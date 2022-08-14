package com.example.app.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="Invoice")
// TODO: Class name start with capital letter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id ;
    @NotNull
    @Column(name="invoiceNum")
    private int invoiceNum ;
    @NotNull
    @Column(name="invoiceDate")
    private LocalDate invoiceDate;
    @NotNull
    @Column(name="subTotalCost")
    private Float subTotalCost ;
    @NotNull
    @Column(name="tax")
    private Float tax ;
    @NotNull
    @Column(name="totalCost")
    private Float TotalCost ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id",nullable=true)
    private Users users;
    @OneToMany (mappedBy = "invoice",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Attachment>attachments=new ArrayList<>();

    @OneToMany (mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductInInvoice>productInInvoices=new ArrayList<>();



    // TODO: Use proper cascade type to avoid deletion of invoices if the user gets deleted
    // TODO: When the user gets deleted, what to do with the foreign key, maybe user_id = null
    // TODO: Read about fetching types, lazy vs eager


    public Invoice() {
    }

    public Invoice(int id, int invoiceNum, LocalDate invoiceDate, Float subTotalCost, Float tax, Float totalCost, Users users, List<Attachment> attachments, List<ProductInInvoice> productInInvoices) {
        this.id = id;
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.subTotalCost = subTotalCost;
        this.tax = tax;
        TotalCost = totalCost;
        this.users = users;
        this.attachments = attachments;
        this.productInInvoices = productInInvoices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Float getSubTotalCost() {
        return subTotalCost;
    }

    public void setSubTotalCost(Float subTotalCost) {
        this.subTotalCost = subTotalCost;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Float getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(Float totalCost) {
        TotalCost = totalCost;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<ProductInInvoice> getProductInInvoices() {
        return productInInvoices;
    }

    public void setProductInInvoices(List<ProductInInvoice> productInInvoices) {
        this.productInInvoices = productInInvoices;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceNum=" + invoiceNum +
                ", invoiceDate=" + invoiceDate +
                ", subTotalCost=" + subTotalCost +
                ", tax=" + tax +
                ", TotalCost=" + TotalCost +
                ", users=" + users +
                ", attachments=" + attachments +
                ", productInInvoices=" + productInInvoices +
                '}';
    }
}
