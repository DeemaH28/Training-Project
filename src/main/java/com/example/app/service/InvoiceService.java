package com.example.app.service;

import com.example.app.exception.InvoiceNotFoundException;
import com.example.app.model.Invoice;

import com.example.app.repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InvoiceService {
    @Autowired
    private final InvoiceRepository invoiceRepository;


    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository =invoiceRepository;
    }
    //add and Save
    public Invoice addInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    // find all invoices
    public List<Invoice> findAllInvoices(){
        return (List<Invoice>) invoiceRepository.findAll();
    }
    //update
    public Invoice updateInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

     public void deleteInvoiceById(Integer id)throws InvoiceNotFoundException {

        Long count = invoiceRepository.countById(id);
        if (count == null || count == 0){
            throw new InvoiceNotFoundException("Could not find any invoice with ID "+ id);
        }
        invoiceRepository.deleteById(id);
    }

    public Invoice findInvoiceById(Integer id) throws InvoiceNotFoundException {
        Optional<Invoice> result = invoiceRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new InvoiceNotFoundException("Could not find any Invoice with ID "+ id);
    }


}
