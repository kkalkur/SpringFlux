package com.wirebraincoffe.productapiannotation.controller;
import com.wirebraincoffe.productapiannotation.Service.ProductService;
import com.wirebraincoffe.productapiannotation.exception.InvalidParamterException;
import com.wirebraincoffe.productapiannotation.model.Product;
import com.wirebraincoffe.productapiannotation.repository.ProductRepository;
import com.wirebraincoffe.productapiannotation.util.ValidateUtil;
import com.wirebraincoffe.productapiannotation.vo.enrollementQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;


@RestController
@RequestMapping("/products")

public class ProductController {

    private ProductRepository repository;

   @Autowired
    private ProductService productService;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Product> getAllProducts() {

        // return repository.findAll();

        return productService.getProduct();
    }

    @GetMapping("{id}")

    public Mono<ResponseEntity<Product>> getProduct(@PathVariable String id) {
        return repository.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty((ResponseEntity.notFound().build()));
    }


    @PostMapping(path = "search")
    public Mono<ResponseEntity<Product>> getEnrollment(@RequestParam Map<String,String> customQuery,@RequestBody Product product) {
        System.out.println("customQuery = program " + customQuery.containsKey("program"));
        System.out.println("customQuery = limit " + customQuery.containsKey("limit"));
        System.out.println("customQuery = provider " + customQuery.containsKey("provider"));
        System.out.println("customQuery = other " + customQuery.containsKey("other"));
        System.out.println("customQuery = sort " + customQuery.containsKey("sort"));


      return ValidateUtil.validatePatemeter(customQuery).
              flatMap((infoVo)->productService.getProduct(infoVo)).
              map((productResponse)-> {
          return  ResponseEntity.ok(productResponse);});


       /* return ValidateUtil.validatePatemeter(customQuery).map((inputVo)-> {
            return productService.getProduct(inputVo);
        }).map((product1)-> {
            return  ResponseEntity.ok(product1);
        }); */




      //  return Mono.just(ResponseEntity.ok(product));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> saveProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable(value = "id") String id,
                                                       @RequestBody Product product) {
        return repository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    return repository.save(existingProduct);
                })
                .map(updateProduct -> ResponseEntity.ok(updateProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable(value = "id") String id) {
        return repository.findById(id)
                .flatMap(existingProduct ->
                        repository.delete(existingProduct)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllProducts() {
        return repository.deleteAll();
    }

    @ExceptionHandler(InvalidParamterException.class)
    public ResponseEntity invalidparameterException(InvalidParamterException ex) {
        return ResponseEntity.ok("Invalid Product"+ ex.getMessage());
    }



}
