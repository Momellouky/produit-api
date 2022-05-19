package com.nawratech.back.produit.controllers;

import com.nawratech.back.produit.models.Product;
import com.nawratech.back.produit.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/produits")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ResponseEntity<List<Product>>  findAllProducts(@RequestParam("limit") Optional<Integer> limit,
                                                                 @RequestParam("order") Optional<String> order){

        List<Product> products;

        if(order.isPresent()){

            if(limit.isPresent()){

                products = productService.findProductsLimitNOrdered(limit.get(), order.get());



            }else{
                products = productService.findOrderedProducts(order.get());
            }

        }else if(limit.isPresent()){

            products = productService.findProductsLimitN(limit.get());

        }else {
            products = productService.findAllProducts();
        }



        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


//    public CollectionModel<EntityModel<Product>> findSubsetOfProducts(@RequestParam("limit") String limit){
//        System.out.println("Product limit" + limit);
//        return null;
//    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){

        Product product = productService.findProductById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);


    }

    @PostMapping
    public ResponseEntity<Product> insertProduct(@RequestBody Product productToInsert){

        Product insertedProduct =  productService.insertProduct(productToInsert);

        return new ResponseEntity<Product>(insertedProduct, HttpStatus.CREATED);

    }

    /**
     *
     * @param newProduct : newer values of the product
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product newProduct){

        Product modifiedProduct = productService.updateProduct(newProduct.getId(), newProduct);

        return new ResponseEntity<Product>(modifiedProduct, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void deleteProductById(@PathVariable Long id){

        productService.deleteProductById(id);

    }

}
