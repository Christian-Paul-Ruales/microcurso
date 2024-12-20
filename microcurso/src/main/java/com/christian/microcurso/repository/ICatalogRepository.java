package com.christian.microcurso.repository;

import com.christian.microcurso.entity.Catalog;
import org.springframework.data.repository.CrudRepository;

public interface ICatalogRepository extends CrudRepository<Catalog, Integer> {
    
}
