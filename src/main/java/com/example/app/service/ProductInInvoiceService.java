package com.example.app.service;

import com.example.app.exception.InvoiceNotFoundException;
import com.example.app.exception.ProductInInvoiceNotFoundException;
import com.example.app.exception.ProductNotFoundException;
import com.example.app.model.Invoice;
import com.example.app.model.ProductInInvoice;
import com.example.app.repository.ProductInInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInInvoiceService {
    @Autowired
    private final ProductInInvoiceRepository productInInvoiceRepository;

    public ProductInInvoiceService(ProductInInvoiceRepository productInInvoiceRepository) {
        this.productInInvoiceRepository = productInInvoiceRepository;
    }


    //add and Save
    public ProductInInvoice addItem(ProductInInvoice productInInvoice){
        return productInInvoiceRepository.save(productInInvoice);
    }

    // find all items
    public List<ProductInInvoice> findAllItems(int pageNo, int pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ProductInInvoice> pagedResult = productInInvoiceRepository.findAll(paging);

        return pagedResult.toList();
    }
    //update
    public ProductInInvoice updateItem(ProductInInvoice productInInvoice){
        Optional<ProductInInvoice> productInInvoiceOptional = productInInvoiceRepository.findById(productInInvoice.getID());
        if (productInInvoiceOptional.isEmpty()) {
            return productInInvoiceRepository.save(productInInvoice);

        }
        ProductInInvoice productInInvoice1 = productInInvoiceOptional.get();
        productInInvoice1 .setProductQuantity(productInInvoice.getProductQuantity());
        productInInvoice1 .setID(productInInvoice.getID());
        productInInvoice1 .setTotalPrice(productInInvoice.getTotalPrice());
        return productInInvoiceRepository.save(productInInvoice1 );
    }

    public void deleteItemById(Integer id) throws ProductInInvoiceNotFoundException {
        Optional<ProductInInvoice> result = productInInvoiceRepository.findById(id);
        if (result.isPresent()){
            productInInvoiceRepository.deleteById(id);;
        }
        else {
            throw new ProductInInvoiceNotFoundException("Could not find any Item with ID " + id);

        }
    }

    public ProductInInvoice findItemById(Integer id) throws ProductInInvoiceNotFoundException {
        Optional<ProductInInvoice> result = productInInvoiceRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ProductInInvoiceNotFoundException ("Could not find any Item with ID "+ id);
    }

}
