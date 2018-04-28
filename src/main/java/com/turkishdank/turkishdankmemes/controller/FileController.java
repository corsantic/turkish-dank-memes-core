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

        InputStream soundStream = new FileInputStream("D:\\JavaProjects\\turkish-dank\\turkish-dank-memes\\src\\main\\resources\\assets\\" + audio);
        if (soundStream == null)
            return ResponseEntity.badRequest().body("File Not Found");
        metaData.put("type", "audio");

        // Store file to MongoDB
        soundFileId = gridOperations.store(soundStream, audio, "audio/mp3", metaData).getId().toString();
        System.out.println("SoundFileId = " + soundFileId);


        return ResponseEntity.ok().body("File Uploaded to MongoDb");
    }


}