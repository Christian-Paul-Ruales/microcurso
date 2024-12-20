package com.christian.microcurso.controller;

import com.christian.microcurso.entity.Catalog;
import com.christian.microcurso.repository.ICatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    /**
     * donde esta realizando las operaciones crud
     * */
    @Autowired
    private ICatalogRepository catalogRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Catalog> getAllProducts(){
        return (List<Catalog>) catalogRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createProduct(@RequestBody Catalog productEntity){
        catalogRepository.save(productEntity);
    }
}
