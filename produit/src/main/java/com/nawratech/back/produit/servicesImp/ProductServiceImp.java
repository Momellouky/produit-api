package com.nawratech.back.produit.servicesImp;

import com.nawratech.back.produit.exceptionHandlers.HttpBadRequestException;
import com.nawratech.back.produit.exceptionHandlers.HttpUnprocessableEntityException;
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

    /**
     *
     * @param : product
     * @return : inserted product
     * @throws HttpUnprocessableEntityException in case the product already exists in the database
     * @throws HttpBadRequestException in case the given product has a negative id
     *
     * -----------------------------------------------------------------------------------------------
     *
     * isEmpty() method
     *      @return  true  : if  the value is not present - the value isn't in the database
     *      @return false : otherwise
     *
     *      using not ( ! ) to match the result of productRepo.findById(productId).isEmpty()
     *      with the variable name isTheProductPresent
     *
     */
    @Override
    public Product insertProduct(Product product) throws HttpUnprocessableEntityException, HttpBadRequestException{

        String productName = product.getName();

        if(productName == null){
            throw new HttpBadRequestException(productName);
        }




        boolean isProductNameNotValid =  productName.length() > 100 || productName.length() == 0;

        System.out.println("Product name lenght: " + productName.length());

        if(  isProductNameNotValid ){
            throw new HttpBadRequestException(productName);
        }

        Long productId = product.getId();

        if( productId < 0 ){
            throw new HttpBadRequestException(productId);
        }

        boolean isTheProductPresent =  ! productRepo.findById(productId).isEmpty();

       if( isTheProductPresent ){

           //
           // we don't need to insert the product, otherwise, we end up with duplicate products.
           //

           throw new HttpUnprocessableEntityException(productId);

       }else {

           return productRepo.save(product);

       }


    }

    @Override
    public Product updateProduct(Long id, Product newProduct) {

        Product product = findProductById(id);

        return productRepo.save(newProduct);

    }


}
