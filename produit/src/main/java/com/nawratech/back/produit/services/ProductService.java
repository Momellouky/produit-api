package com.nawratech.back.produit.services;

import com.nawratech.back.produit.models.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAllProducts();
    public Product findProductById(Long id);

    public Product insertProduct(Product product);

    public Product updateProduct(Long id, Product product);

    /**
     *
     * @param id : the products id
     * @return product: the deleted product [ if exists ]
     */
    public Product deleteProduct(Long id);

}
