package com.example.app.controller;

import com.example.app.DTO.ProductDto;
import com.example.app.exception.ProductNotFoundException;
import com.example.app.model.Product;
import com.example.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
        List<Product> products  = productService.findAllProducts(pageNo, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        Product product= productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
   // @RequestHeader("Authorization") String token
    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto){
        Product newProduct = productService.addProduct(productDto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product updateProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) throws ProductNotFoundException{
        productService.deleteProductById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
