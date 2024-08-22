package com.aurickcode.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.net.URL;
import java.time.LocalDateTime;


@Entity
public class FileEntity {
    @Id
    private String file_id;
    private LocalDateTime upload_date;
    private String password;
    private Integer no_of_days;
    private URL download_link;

    public FileEntity() {
    }

    public FileEntity(String file_id, LocalDateTime upload_date, String password, Integer no_of_days, URL download_link) {
        this.file_id = file_id;
        this.upload_date = upload_date;
        this.password = password;
        this.no_of_days = no_of_days;
        this.download_link = download_link;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public void setUpload_date(LocalDateTime upload_date) {
        this.upload_date = upload_date;
    }

    public LocalDateTime getUpload_date() {
        return upload_date;
    }

    public String getFile_id() {
        return file_id;
    }

    public LocalDateTime getFile_name() {
        return upload_date;
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

    public void setFile_name(LocalDateTime upload_date) {
        this.upload_date = upload_date;
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
