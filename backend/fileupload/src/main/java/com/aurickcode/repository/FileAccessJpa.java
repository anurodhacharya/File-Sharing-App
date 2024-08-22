package com.aurickcode.repository;

import com.aurickcode.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAccessJpa extends JpaRepository<FileEntity, String> {
}
