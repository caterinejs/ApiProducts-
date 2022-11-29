package caterinejs.apiproductos.repository;


import caterinejs.apiproductos.domain.ProductsDomain;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProductsRepository extends ReactiveCrudRepository<ProductsDomain,String> {

    @Query("{'name':?0}")
    public Mono<ProductsDomain> findByName(String name);
}
