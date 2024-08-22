package com.aurickcode.dao;

import com.aurickcode.entity.FileEntity;

import java.util.Optional;

public interface FileSharingDAO {
    FileEntity uploadFile(FileEntity entity);
    Optional<FileEntity> downloadFile(String fileId);
}
