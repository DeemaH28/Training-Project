package com.example.app.service;

import com.example.app.DTO.ProductDto;
import com.example.app.exception.InvoiceNotFoundException;
import com.example.app.exception.ProductNotFoundException;
import com.example.app.model.Invoice;
import com.example.app.model.Product;
import com.example.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //add and Save
    public Product addProduct(ProductDto productDto){
        Product product =new Product();
        product.setID(productDto.getId());
        product.setProductType(productDto.getProductType());
        product.setProductDescription(productDto.getProductDescription());
        product.setPrice(productDto.getPrice());
        return productRepository.save(product);
    }

    // find all invoices
    public List<Product> findAllProducts(int pageNo, int pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);

        return pagedResult.toList();
    }
    //update
    public Product updateProduct(Product product){

        Optional<Product> productOptional = productRepository.findById(product.getID());
        if (productOptional.isEmpty()) {
            return productRepository.save(product);

        }
        Product product1 = productOptional.get();
        product1.setProductDescription(product.getProductDescription());
        product1.setID(product.getID());
        product1.setProductType(product.getProductType());
        product1.setPrice(product.getPrice());
        product1.setProductInInvoices(product.getProductInInvoices());
        return productRepository.save(product1);
    }

    public void deleteProductById(Integer id)throws ProductNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()){
            productRepository.deleteById(id);;
        }
        else {
            throw new ProductNotFoundException("Could not find any product with ID " + id);

        }
    }

    public Product findProductById(Integer id) throws ProductNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ProductNotFoundException ("Could not find any product with ID "+ id);
    }
}
