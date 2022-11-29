package caterinejs.apiproductos.controllers.products;

import caterinejs.apiproductos.domain.ProductsDomain;
import caterinejs.apiproductos.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/rest")

public class ProductsController {

    @Autowired
     private ProductsService productsService;

    @GetMapping("/productos")
     public Mono<ProductsDomain> get(@RequestParam( required = true) String name){

        return productsService.get(name);
    }

    @GetMapping("/productos/all")
    public Flux<ProductsDomain> get(){

        return productsService.getAll();
    }

    @PostMapping("/productos")
    public Mono<ProductsDomain> create(@Valid @RequestBody ProductsDomain productosDomain) {

        return productsService.save(productosDomain);
    }
    @PutMapping("/productos")
    public Mono<ResponseEntity<ProductsDomain>> update(@RequestParam(required = true) String productosDomainId,
                                                       @Valid @RequestBody ProductsDomain  productsDomain){
        return productsService.update(productosDomainId,  productsDomain).map(updateProductsDomain -> new ResponseEntity<>
                (updateProductsDomain, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/productos")
    public Mono<ResponseEntity<Void>> delete (@RequestParam(required = true) String productosDomainId) {

        return productsService.delete(productosDomainId).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
