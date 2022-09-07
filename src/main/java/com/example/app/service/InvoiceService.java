package com.example.app.service;

import com.example.app.DTO.InvoiceDto;
import com.example.app.exception.InvoiceNotFoundException;
import com.example.app.model.Invoice;

import com.example.app.model.User;
import com.example.app.repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Invoice addInvoice(InvoiceDto invoiceDto){
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDto.getId());
        invoice.setInvoiceNum(invoiceDto.getInvoiceNum());
        invoice.setInvoiceDate(invoiceDto.getInvoiceDate());
        invoice.setSubTotalCost(invoiceDto.getSubTotalCost());
        invoice.setTax(invoiceDto.getTax());
        invoice.setTotalCost(invoiceDto.getTotalCost());
        return invoiceRepository.save(invoice);
    }
    // find all invoices
    public List<Invoice> findAllInvoices(int pageNo, int pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Invoice> pagedResult = invoiceRepository.findAll(paging);

        return pagedResult.toList();
    }

    //update
    public Invoice updateInvoice(Invoice invoice){
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoice.getId());
        if (invoiceOptional.isEmpty()) {
            return invoiceRepository.save(invoice);

        }
        Invoice invoice1 = invoiceOptional.get();
        invoice1.setInvoiceDate(invoice.getInvoiceDate());
        invoice1.setId(invoice.getId());
        invoice1.setInvoiceNum(invoice.getInvoiceNum());
        invoice1.setSubTotalCost(invoice.getSubTotalCost());
        invoice1.setTax(invoice.getTax());
        invoice1.setTotalCost(invoice.getTotalCost());
        invoice1.setUsers(invoice.getUsers());
        invoice1.setAttachments(invoice.getAttachments());
        invoice1.setProductInInvoices(invoice.getProductInInvoices());
        return invoiceRepository.save(invoice1);
    }

     public void deleteInvoiceById(Integer id)throws InvoiceNotFoundException {


         Optional<Invoice> result = invoiceRepository.findById(id);
         if (result.isPresent()){
             invoiceRepository.deleteById(id);;
         }
         else {
             throw new InvoiceNotFoundException("Could not find any Invoice with ID " + id);

         }
    }

    public Invoice findInvoiceById(Integer id) throws InvoiceNotFoundException {
        Optional<Invoice> result = invoiceRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new InvoiceNotFoundException("Could not find any Invoice with ID "+ id);
    }


}
