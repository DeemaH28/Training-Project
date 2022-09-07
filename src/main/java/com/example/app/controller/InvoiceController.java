package com.example.app.controller;

import com.example.app.DTO.InvoiceDto;
import com.example.app.exception.InvoiceNotFoundException;
import com.example.app.model.Invoice;
import com.example.app.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")

public class InvoiceController {
    @Autowired
    private final  InvoiceService invoiceService;
   // private final ConfirmationTokenService confirmationTokenService;
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
       // this.confirmationTokenService = confirmationTokenService;
    }
// @GetMapping(path = "deema")
//    public String deema(@RequestHeader("Authorization") String token){
//    ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(()->
//            new IllegalStateException("token not found"));
//        return "done";
//    }
//    @GetMapping()
//    public ResponseEntity<List<Invoice>> getAllInvoices(@RequestHeader("Authorization") String token ,@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
//        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).
//                orElseThrow(()-> new IllegalStateException("token not found"));
//        List<Invoice> invoices = invoiceService.findAllInvoices(pageNo, pageSize);
//        return new ResponseEntity<>( HttpStatus.OK);
//    }
//    @GetMapping
//    public  List<Invoice> get(@RequestHeader("Authorization") String token,@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
//      //  ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).
//               orElseThrow(()-> new IllegalStateException("token not found"));
//        return invoiceService.findAllInvoices(pageNo, pageSize);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Integer id) throws InvoiceNotFoundException {
        Invoice invoice = invoiceService.findInvoiceById(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Invoice> addInvoice(@RequestBody InvoiceDto invoiceDto,@RequestHeader("Authorization") String token){
        Invoice newInvoice = invoiceService.addInvoice(invoiceDto);
        return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice){
        Invoice updateInvoice = invoiceService.updateInvoice(invoice);
        return new ResponseEntity<>(updateInvoice, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable("id") Integer id) throws InvoiceNotFoundException {
        invoiceService.deleteInvoiceById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
