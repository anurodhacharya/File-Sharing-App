package com.aurickcode.entity;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class FileEntity {
    @Id
    private String file_id;
    private String file_name;
    private String password;
    private int no_of_days;
    private String download_link;

    public FileEntity(String file_id, String file_name, String password, int no_of_days, String download_link) {
        this.file_id = file_id;
        this.file_name = file_name;
        this.password = password;
        this.no_of_days = no_of_days;
        this.download_link = download_link;
    }

    public String getFile_id() {
        return file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getPassword() {
        return password;
    }

    public int getNo_of_days() {
        return no_of_days;
    }

    public String getDownload_link() {
        return download_link;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNo_of_days(int no_of_days) {
        this.no_of_days = no_of_days;
    }

    public void setDownload_link(String download_link) {
        this.download_link = download_link;
    }
}
