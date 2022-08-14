package com.example.app.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "ProductInInvoice")
// TODO: use camel case for class name
public class ProductInInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "productQuantity")
    private int productQuantity;

    @NotNull
    @Column(name = "totalPrice")
    private Float totalPrice;
    @ManyToOne
    @JoinColumn(name = "invoiceId",referencedColumnName = "id",nullable=true)
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id",nullable=true)
    private Product product;

    public ProductInInvoice() {
    }

    public ProductInInvoice(int id, int productQuantity, Float totalPrice, Invoice invoice, Product product) {
        this.id = id;
        this.productQuantity = productQuantity;
        this.totalPrice = totalPrice;
        this.invoice = invoice;
        this.product = product;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = id;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductInInvoice{" +
                "id=" + id +
                ", productQuantity=" + productQuantity +
                ", totalPrice=" + totalPrice +
                ", invoice=" + invoice +
                ", product=" + product +
                '}';
    }
}