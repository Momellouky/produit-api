package com.nawratech.back.produit.services;

import com.nawratech.back.produit.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

     public List<Product> findAllProducts();

     public List<Product> findProductsLimitN(int limit);

     public List<Product> findOrderedProducts(String orderStrategy);
     public Product findProductById(Long id);

    public Product insertProduct(Product product);

    public Product updateProduct(Long id, Product product);

    /**
     *
     * @param id : the products id
     * @return product: the deleted product [ if exists ]
     */
    public Product deleteProductById(Long id);

    public List<Product> findProductsLimitNOrdered(Integer integer, String s);
}
