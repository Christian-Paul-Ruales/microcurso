package com.christian.stock_microservice.controller;

import com.christian.stock_microservice.entity.StockEntity;
import com.christian.stock_microservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/{code}")
    public boolean stockDisponible(@PathVariable("code") String code) {
        Optional<StockEntity> optStock = stockRepository.findByCode(code);
        optStock.orElseThrow( () -> new RuntimeException(String.format("Cannot find the product '%s'", code)));

        return optStock.get().getQuantity() > 0;
    }
}
