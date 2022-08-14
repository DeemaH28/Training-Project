package com.example.app.controller;

import com.example.app.exception.InvoiceNotFoundException;
import com.example.app.model.Invoice;
import com.example.app.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")

public class InvoiceController {
    @Autowired
    private final  InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> getAllInvoices(){
        List<Invoice> invoices = invoiceService.findAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Integer id) throws InvoiceNotFoundException {
        Invoice invoice = invoiceService.findInvoiceById(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice){
        Invoice newInvoice = invoiceService.addInvoice(invoice);
        return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice){
        Invoice updateInvoice = invoiceService.updateInvoice(invoice);
        return new ResponseEntity<>(updateInvoice, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable("id") Integer id) throws InvoiceNotFoundException {
          invoiceService.deleteInvoiceById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
