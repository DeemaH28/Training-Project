package com.example.app.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table (name = "Attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;

    @NotNull
    @Column(name = "url")
    private String url ;

    @NotNull
    @Column(name = "size")
    private Float size ;

    @Enumerated (EnumType.STRING)
    @Column(name="attachType")
    private AttachType attachType;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoiceId", referencedColumnName = "id",nullable=true)
    private Invoice invoice;

    public Attachment() {
    }

    public Attachment(int id, String url, Float size, AttachType attachType, Invoice invoice) {
        this.id = id;
        this.url = url;
        this.size = size;
        this.attachType = attachType;
        this.invoice = invoice;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AttachType getAttachType() {
        return attachType;
    }

    public void setAttachType(AttachType attachType) {
        this.attachType = attachType;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", size=" + size +
                ", attachType=" + attachType +
                ", invoice=" + invoice +
                '}';
    }
}
