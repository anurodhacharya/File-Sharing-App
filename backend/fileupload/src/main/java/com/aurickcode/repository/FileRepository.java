package com.aurickcode.repository;

import com.aurickcode.dao.FileSharingDAO;
import com.aurickcode.entity.FileEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FileRepository implements FileSharingDAO {

    private FileAccessJpa fileAccessJpa;

    public FileRepository(FileAccessJpa fileAccessJpa) {
        this.fileAccessJpa = fileAccessJpa;
    }

    @Override
    public FileEntity uploadFile(FileEntity entity) {
        return fileAccessJpa.save(entity);
    }

    @Override
    public Optional<FileEntity> downloadFile(String fileId) {
        return fileAccessJpa.findById(fileId);
    }
}
