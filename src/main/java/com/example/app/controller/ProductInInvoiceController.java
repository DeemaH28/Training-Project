package com.example.app.controller;

import com.example.app.exception.ProductInInvoiceNotFoundException;
import com.example.app.exception.ProductNotFoundException;
import com.example.app.model.Product;
import com.example.app.model.ProductInInvoice;
import com.example.app.service.ProductInInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ProductInInvoiceController {

    @Autowired
    private final ProductInInvoiceService productInInvoiceService;

    public ProductInInvoiceController(ProductInInvoiceService productInInvoiceService) {
        this.productInInvoiceService = productInInvoiceService;
    }


    @GetMapping()
    public ResponseEntity<List<ProductInInvoice>> getAllItems(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
        List<ProductInInvoice> allProducts  = productInInvoiceService.findAllItems(pageNo, pageSize);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductInInvoice> getItemById(@PathVariable("id") Integer id) throws ProductInInvoiceNotFoundException {
        ProductInInvoice productInInvoice= productInInvoiceService.findItemById(id);
        return new ResponseEntity<>(productInInvoice, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductInInvoice> addItem(@RequestBody ProductInInvoice productInInvoice){
        ProductInInvoice newItem = productInInvoiceService.addItem(productInInvoice);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ProductInInvoice> updateItem(@RequestBody ProductInInvoice productInInvoice){
        ProductInInvoice updateItem = productInInvoiceService.updateItem(productInInvoice);
        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) throws ProductInInvoiceNotFoundException{
        productInInvoiceService.deleteItemById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
