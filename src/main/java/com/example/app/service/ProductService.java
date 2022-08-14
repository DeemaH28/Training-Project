package com.example.app.service;

import com.example.app.exception.ProductNotFoundException;
import com.example.app.model.Product;
import com.example.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    // find all invoices
    public List<Product> findAllProducts(){
        return (List<Product>) productRepository.findAll();
    }
    //update
    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProductById(Integer id)throws ProductNotFoundException {

        Long count = productRepository.countById(id);
        if (count == null || count == 0){
            throw new ProductNotFoundException("Could not find any product with ID "+ id);
        }
        productRepository.deleteById(id);
    }

    public Product findProductById(Integer id) throws ProductNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ProductNotFoundException ("Could not find any product with ID "+ id);
    }
}
