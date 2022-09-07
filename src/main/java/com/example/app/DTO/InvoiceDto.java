package com.example.app.DTO;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import java.time.LocalDate;

public class InvoiceDto {

    private int id ;

    private int invoiceNum ;

    private LocalDate invoiceDate;

    private Float subTotalCost ;

    private Float tax ;

    private Float totalCost ;

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
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "InvoiceDto{" +
                "id=" + id +
                ", invoiceNum=" + invoiceNum +
                ", invoiceDate=" + invoiceDate +
                ", subTotalCost=" + subTotalCost +
                ", tax=" + tax +
                ", totalCost=" + totalCost +
                '}';
    }
}
