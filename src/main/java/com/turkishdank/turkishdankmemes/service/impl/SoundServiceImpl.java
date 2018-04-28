package com.turkishdank.turkishdankmemes.service.impl;

import com.turkishdank.turkishdankmemes.dao.SoundRepository;
import com.turkishdank.turkishdankmemes.entity.Sound;
import com.turkishdank.turkishdankmemes.service.FileService;
import com.turkishdank.turkishdankmemes.service.SoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SoundServiceImpl  implements SoundService {


    @Autowired
    SoundRepository soundRepository;

    @Override
    public void save(Sound sound)
    {
        soundRepository.save(sound);
    }


    @Override
    public List<Sound> loadAll()
    {
        return soundRepository.loadAll();
    }

    @Override
    public Sound loadByName(String name)
    {
        return soundRepository.loadByName(name);
    }
}
