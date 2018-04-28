package com.turkishdank.turkishdankmemes.service;

import com.mongodb.gridfs.GridFSFile;

import java.util.List;

public interface FileService {


List<GridFSFile> loadAll();

GridFSFile loadByName(String name);



}
