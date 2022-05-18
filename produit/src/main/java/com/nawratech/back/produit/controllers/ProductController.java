package com.nawratech.back.produit.controllers;

import com.nawratech.back.produit.models.Product;
import com.nawratech.back.produit.productAssembler.ProductAssembler;
import com.nawratech.back.produit.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/produits")
public class ProductController {

    private ProductService productService;
    private ProductAssembler productAssembler;

    @Autowired
    public ProductController(ProductService productService, ProductAssembler productAssembler) {
        this.productService = productService;
        this.productAssembler = productAssembler;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public CollectionModel<EntityModel<Product>> findAllProducts(@RequestParam("limit") Optional<Integer> limit,
                                                                 @RequestParam("order") Optional<String> order){

        List<EntityModel<Product>> products;

        if(order.isPresent()){

            if(limit.isPresent()){
                products = productService.findProductsLimitNOrdered(limit.get(), order.get()).stream().map(
                        product -> {
                            return productAssembler.toModel(product);
                        }
                ).collect(Collectors.toList());
            }else{
                products = productService.findOrderedProducts(order.get()).stream().map(
                        product -> {
                            return productAssembler.toModel(product);
                        }
                ).collect(Collectors.toList());
            }

        }else if(limit.isPresent()){
            products = productService.findProductsLimitN(limit.get()).stream().map(
                    product -> {
                        return productAssembler.toModel(product);
                    }
            ).collect(Collectors.toList());
        }else {
            products = productService.findAllProducts().stream() //
                    .map( product -> {
                                return productAssembler.toModel(
                                        product
                                );
                            }

                    ) //
                    .collect(Collectors.toList());
        }



        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).findAllProducts(null, null)).withSelfRel());

    }


//    public CollectionModel<EntityModel<Product>> findSubsetOfProducts(@RequestParam("limit") String limit){
//        System.out.println("Product limit" + limit);
//        return null;
//    }

    @GetMapping("{id}")
    public EntityModel<Product> findProductById(@PathVariable Long id){

        Product product = productService.findProductById(id);


        return productAssembler.toModel(product);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<Product> insertProduct(@RequestBody Product productToInsert){

        Product insertedProduct =  productService.insertProduct(productToInsert);

        return productAssembler.toModel(insertedProduct);

    }

    /**
     *
     * @param newProduct : newer values of the product
     * @return
     */
    @PutMapping("/{id}")
    public EntityModel<Product> updateProduct(@RequestBody Product newProduct){

        Product modifiedProduct = productService.updateProduct(newProduct.getId(), newProduct);
        return productAssembler.toModel(modifiedProduct);

    }

    @DeleteMapping("/{id}")
    public EntityModel<Product> deleteProductById(@PathVariable Long id){

        Product deletedProduct = productService.deleteProductById(id);

        return productAssembler.toModel(deletedProduct);

    }

}
