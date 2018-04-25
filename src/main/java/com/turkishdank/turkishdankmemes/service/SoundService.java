package com.turkishdank.turkishdankmemes.service;

import com.turkishdank.turkishdankmemes.entity.Sound;

import java.util.List;

public interface SoundService {

    void save(Sound sound);

    List<Sound> loadAll();

    Sound loadByName(String name);
}
