package com.nawratech.back.produit.servicesImp;

import com.nawratech.back.produit.exceptionHandlers.ErrorMessages;
import com.nawratech.back.produit.exceptionHandlers.HttpBadRequestException;
import com.nawratech.back.produit.exceptionHandlers.HttpUnprocessableEntityException;
import com.nawratech.back.produit.exceptionHandlers.RessourceNotFoundException;
import com.nawratech.back.produit.models.Product;
import com.nawratech.back.produit.repositories.ProductRepo;
import com.nawratech.back.produit.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Product> findProductsLimitN(int limit) throws RessourceNotFoundException, HttpBadRequestException{

        //
        // case : limit > data.size() -> Api return the hole set of data.
        //


        if( limit <= 0 ){
            throw new HttpBadRequestException(ErrorMessages.INVALID_QUERY_PARAM_LIMIT);
        }

        List<Product> products = productRepo.findLimitedProducts(limit);

        if(products.isEmpty()){
            throw new RessourceNotFoundException();
        }

        return products;

    }

    @Override
    public Product findProductById(Long id) throws HttpBadRequestException {

        if(id < 0) {
            throw new HttpBadRequestException(ErrorMessages.NEGATIVE_ID);
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

        validateProduct(product);

        Long productId = product.getId();

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

        product.setName(newProduct.getName());
        product.setQuantity(newProduct.getQuantity());

        validateProduct(product);

        return productRepo.save(product);

    }

    @Override
    public Product deleteProductById(Long id) {

        Product productToDelete = findProductById(id);

        productRepo.delete(productToDelete);

        return productToDelete;

    }

    private boolean validateProduct(Product product){
        String productName = product.getName();

        if(productName == null){
            throw new HttpBadRequestException(ErrorMessages.INVALID_NAME);
        }

        boolean isProductNameNotValid =  productName.length() > 100 || productName.length() == 0;
        

        if(  isProductNameNotValid ){
            throw new HttpBadRequestException(ErrorMessages.INVALID_NAME);
        }

        Long productId = product.getId();

        if( productId < 0 ){
            throw new HttpBadRequestException(ErrorMessages.NEGATIVE_ID);
        }

        int productQuantity = product.getQuantity();

        if(productQuantity < 0){
            throw new HttpBadRequestException(ErrorMessages.NEGATIVE_QUANTITY);
        }

        return true;
    }


}
