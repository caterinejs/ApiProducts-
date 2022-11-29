package caterinejs.apiproductos.service;

import caterinejs.apiproductos.domain.ProductsDomain;
import caterinejs.apiproductos.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public Mono<ProductsDomain> get(String name) {
        return productsRepository.findByName(name);
    }

    public Flux<ProductsDomain> getAll() {
        return productsRepository.findAll();
    }

    public Mono<ProductsDomain> save(ProductsDomain name) {
        return productsRepository.save(name);
    }

    public Mono<ProductsDomain> update(String id, ProductsDomain productsDomain){
        return productsRepository.findById(id).flatMap(existingProductsDomain -> {
            existingProductsDomain.setName(
                    productsDomain.getName().isEmpty() ? existingProductsDomain.getName() : productsDomain.getName());
            existingProductsDomain.setPrecio(
                    productsDomain.getPrecio() == null ? existingProductsDomain.getPrecio() : productsDomain.getPrecio());
            existingProductsDomain.setOwnerName(
                    productsDomain.getOwnerName().isEmpty() ? existingProductsDomain.getOwnerName() : productsDomain.getOwnerName());
            existingProductsDomain.setOwnerEmail(
                    productsDomain.getOwnerEmail().isEmpty() ? existingProductsDomain.getOwnerEmail() : productsDomain.getOwnerEmail());
            return productsRepository.save(existingProductsDomain);
        });
    }
    public Mono<Void> delete(String id) {
        return productsRepository.findById(id).flatMap(existingProductsDomain ->
                productsRepository.deleteById(id));
    }

}