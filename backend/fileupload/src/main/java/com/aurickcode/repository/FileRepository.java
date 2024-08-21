package com.aurickcode.repository;

import com.aurickcode.dao.FileSharingDAO;
import com.aurickcode.entity.FileEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepository implements FileSharingDAO {

    private FileAccessJpa fileAccessJpa;

    public FileRepository(FileAccessJpa fileAccessJpa) {
        this.fileAccessJpa = fileAccessJpa;
    }

    @Override
    public void uploadFile(FileEntity entity) {
        fileAccessJpa.save(entity);
    }
}
