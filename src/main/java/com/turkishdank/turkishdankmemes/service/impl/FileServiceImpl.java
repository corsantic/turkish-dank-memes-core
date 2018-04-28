package com.turkishdank.turkishdankmemes.service.impl;

import com.mongodb.gridfs.GridFSFile;
import com.turkishdank.turkishdankmemes.dao.FileRepository;
import com.turkishdank.turkishdankmemes.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileRepository fileRepository;

    @Override
    public List<GridFSFile> loadAll() {
        return fileRepository.loadAll();
    }

    @Override
    public GridFSFile loadByName(String name) {
        return fileRepository.loadByName(name);
    }


}
