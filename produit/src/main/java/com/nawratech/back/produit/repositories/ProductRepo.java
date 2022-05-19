package com.nawratech.back.produit.repositories;

import com.nawratech.back.produit.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM produits order by id asc limit :limit", nativeQuery = true)
    public List<Product> findLimitedProducts(@Param("limit") int limit);

    public List<Product> findByOrderByIdAsc();

    public List<Product> findByOrderByIdDesc();

/*
    @Query(value = "SELECT * FROM produits order by id :orderStrategy limit :limit", nativeQuery = true)
    public List<Product> findOrderedProductsLimitN(@Param("orderStrategy") String orderStrategy, @Param("limit") int limit);

    */

    @Query(value = "SELECT * FROM produits order by id asc limit :limit", nativeQuery = true)
    public List<Product> findOrderByAscLimitN(@Param("limit") int limit);
    @Query(value = "SELECT * FROM produits order by id desc limit :limit", nativeQuery = true)
    public List<Product> findOrderByDescLimitN(@Param("limit") int limit);

}
