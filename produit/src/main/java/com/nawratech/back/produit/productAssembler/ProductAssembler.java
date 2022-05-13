package com.nawratech.back.produit.productAssembler;

import com.nawratech.back.produit.controllers.ProductController;
import com.nawratech.back.produit.models.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {


    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(
                product,
                linkTo(methodOn(ProductController.class).findAllProducts()).withRel("Tout les produits"),
                linkTo(methodOn(ProductController.class).findProductById(product.getId())).withSelfRel()
        );
    }

}