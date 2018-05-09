package com.turkishdank.turkishdankmemes.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import com.turkishdank.turkishdankmemes.entity.Sound;
import com.turkishdank.turkishdankmemes.service.SoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/sound")
public class SoundController
{
    @Autowired
    private SoundService soundService;


    @ResponseBody
    @RequestMapping(value = "/load/all")
    public List<Sound> loadAll()
    {
        return (soundService.loadAll());
    }


    @ResponseBody
    @RequestMapping(value ="/load/{name}")
    public ResponseEntity<Sound> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok().body(soundService.loadByName(name));}


    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public ResponseEntity check()
    {

        String filePathString = "/var/www/audios";
        File folder = new File(filePathString);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                Sound loadedSound = soundService.loadByName(listOfFiles[i].getName());
                if (loadedSound == null)
                {
                    Sound sound = new Sound();


                 String[] str =  listOfFiles[i].getName().split(".mp3");
                    sound.setName(str[0]);
                    sound.setFullName(listOfFiles[i].getName());
                    soundService.save(sound);
                }
            } else if (listOfFiles[i].isDirectory()) {
                return ResponseEntity.ok().body(listOfFiles[i].getName());
            }
        }


        return ResponseEntity.ok().body(true);
    }







    @ResponseBody
    @RequestMapping(value = "/save/{name}", method = RequestMethod.POST)
    public ResponseEntity save(@PathVariable("name") String name)
    {
        Sound loadedSound = soundService.loadByName(name);
        if (loadedSound == null)
        {
            Sound sound = new Sound();
            sound.setName(name);
            sound.setFullName(name+".mp3");

            soundService.save(sound);
        }

        return ResponseEntity.ok().body(true);
    }
}