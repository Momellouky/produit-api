package com.nawratech.back.produit.servicesImp;

import com.nawratech.back.produit.exceptionHandlers.HttpBadRequestException;
import com.nawratech.back.produit.exceptionHandlers.RessourceNotFoundException;
import com.nawratech.back.produit.models.Product;
import com.nawratech.back.produit.repositories.ProductRepo;
import com.nawratech.back.produit.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepo productRepo;

    @Autowired
    public ProductServiceImp(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findAllProducts() throws RessourceNotFoundException {

        List<Product> allProducts = productRepo.findAll();

        if(allProducts.isEmpty()) {
            throw new RessourceNotFoundException();
        }

        return allProducts;

    }

    @Override
    public Product findProductById(Long id) throws HttpBadRequestException {

        if(id < 0) {
            throw new HttpBadRequestException(id);
        }

        return  productRepo.findById(id).orElseThrow(() -> new RessourceNotFoundException());

    }

    @Override
    public Product insertProduct(Product product) {
        return productRepo.save(product);
    }
}
