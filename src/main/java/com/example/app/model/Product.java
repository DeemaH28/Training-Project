package com.example.app.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @NotNull
    @Column(name = "productType")
    private String productType ;

    @NotNull
    @Column(name = "productDescription")
    private String productDescription ;

    @NotNull
    @Column(name = "price")
    private Float price ;
    @OneToMany (mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ProductInInvoice>productInInvoices=new ArrayList<>();

    public Product() {
    }

    public Product(int id, String productType, String productDescription, Float price, List<ProductInInvoice> productInInvoices) {
        this.id = id;
        this.productType = productType;
        this.productDescription = productDescription;
        this.price = price;
        this.productInInvoices = productInInvoices;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<ProductInInvoice> getProductInInvoices() {
        return productInInvoices;
    }

    public void setProductInInvoices(List<ProductInInvoice> productInInvoices) {
        this.productInInvoices = productInInvoices;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productType='" + productType + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                ", productInInvoices=" + productInInvoices +
                '}';
    }
}
