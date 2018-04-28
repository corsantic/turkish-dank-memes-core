package com.turkishdank.turkishdankmemes.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {
 
	@Autowired
	GridFsOperations gridOperations;
	
	// this variable is used to store ImageId for other actions like: findOne or delete
	private String soundFileId = "";
 
	@GetMapping("/save/{audio}")
	public ResponseEntity saveFiles(@PathVariable("audio") String audio) throws FileNotFoundException {
		// Define metaData
		DBObject metaData = new BasicDBObject();
		metaData.put("organization", "sound");
		audio = audio.concat(".mp3");
		/**
		 * 1. save an image file to MongoDB
		 */

		InputStream soundStream = new FileInputStream("D:\\JavaProjects\\turkish-dank\\turkish-dank-memes\\src\\main\\resources\\assets\\"+audio);
		if(soundStream==null)
			return ResponseEntity.badRequest().body("File Not Found");
		metaData.put("type", "audio");
		
		// Store file to MongoDB
		soundFileId = gridOperations.store(soundStream, audio, "audio/mp3", metaData).getId().toString();
		System.out.println("SoundFileId = " + soundFileId);
 
		/**
		 * 2. save text files to MongoDB
		 */
		
		// change metaData
//		metaData.put("type", "data");
//
//		// Store files to MongoDb
//		gridOperations.store(new FileInputStream("D:\\JSA\\text-1.txt"), "text-1.txt", "text/plain", metaData);
//		gridOperations.store(new FileInputStream("D:\\JSA\\text-2.txt"), "text-2.txt", "text/plain", metaData);

		return ResponseEntity.ok().body("File Uploaded to MongoDb");
	}
	
	@GetMapping("/retrieve/sound-file")
	public String retrieveSoundFile() throws IOException{
		// read file from MongoDB
		GridFSDBFile soundFile = gridOperations.findOne(new Query(Criteria.where("_id").is(soundFileId)));
		
		// Save file back to local disk
		soundFile.writeTo("D:\\JavaProjects\\turkish-dank\\turkish-dank-memes\\src\\main\\resources\\assets\\test.mp3");
		
		System.out.println("Sound File Name:" + soundFile.getFilename());
		
		return "Done";
	}
	
	@GetMapping("/retrieve/text-files")
	public String retrieveTextFiles(){
		/**
		 * get all data files then save to local disk
		 */
		
		// Retrieve all data files
		List<GridFSDBFile> textFiles = gridOperations.find(new Query(Criteria.where("metadata.type").is("data")));
		
		// Save all back to local disk
		textFiles.forEach(file->{
			
			try {
				String fileName = file.getFilename();
				
				file.writeTo("D:\\JavaProjects\\turkish-dank\\turkish-dank-memes\\src\\main\\resources\\assets"+ fileName);
				
				System.out.println("Text File Name: " + fileName);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		return "Done";
	}
	
	@GetMapping("/delete/image")
	public String deleteFile(){
		// delete image via id
		gridOperations.delete(new Query(Criteria.where("_id").is(soundFileId)));
		
		return "Done";
	}
}