package com.turkishdank.turkishdankmemes.controller;

import java.util.List;

import com.turkishdank.turkishdankmemes.entity.Sound;
import com.turkishdank.turkishdankmemes.service.SoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<Sound>> loadAll()
    {
        return ResponseEntity.ok().body(soundService.loadAll());
    }


    @ResponseBody
    @RequestMapping(value = "/save/{name}", method = RequestMethod.POST)
    public ResponseEntity save(@PathVariable("name") String name)
    {
        Sound loadedSound = soundService.loadByName(name);
        if (loadedSound == null)
        {
            Sound sound = new Sound();
            // todo: name and surname
            sound.setName("Test " + name);

            soundService.save(sound);
        }

        return ResponseEntity.ok().body(true);
    }
}