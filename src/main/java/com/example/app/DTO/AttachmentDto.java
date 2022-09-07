package com.example.app.DTO;

import com.example.app.model.AttachType;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AttachmentDto {
    private int id ;

    private String url ;

    private Float size ;

    private AttachType attachType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public AttachType getAttachType() {
        return attachType;
    }

    public void setAttachType(AttachType attachType) {
        this.attachType = attachType;
    }
}
