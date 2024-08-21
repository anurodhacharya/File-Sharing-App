package com.aurickcode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.net.URL;

@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_id;
    private String file_name;
    private String password;
    private Integer no_of_days;
    private URL download_link;

    public FileEntity(String file_name, String password, Integer no_of_days, URL download_link) {
        this.file_name = file_name;
        this.password = password;
        this.no_of_days = no_of_days;
        this.download_link = download_link;
    }

    public Long getFile_id() {
        return file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getNo_of_days() {
        return no_of_days;
    }

    public URL getDownload_link() {
        return download_link;
    }

    public void setFile_id(Long file_id) {
        this.file_id = file_id;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNo_of_days(Integer no_of_days) {
        this.no_of_days = no_of_days;
    }

    public void setDownload_link(URL download_link) {
        this.download_link = download_link;
    }
}
