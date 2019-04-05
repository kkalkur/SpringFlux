package com.wirebraincoffe.productapiannotation.Service;

import com.wirebraincoffe.productapiannotation.exception.InvalidParamterException;
import com.wirebraincoffe.productapiannotation.model.Product;
import com.wirebraincoffe.productapiannotation.repository.ProductRepository;
import com.wirebraincoffe.productapiannotation.vo.enrollementQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service("productService")
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Flux<Product> getProduct(){

     // throw new InvalidParamterException("Invalid parameter");
       return repository.findAll();

    }

    public Mono<Product> getProduct(enrollementQuery inputVO){
        return Mono.just(new Product("2232","coffe",34.66));

    }
}
