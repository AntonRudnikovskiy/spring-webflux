package spring.webflux.springwebflux.sec01.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import spring.webflux.springwebflux.sec01.entity.ProductEntity;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {

    Flux<ProductEntity> findByPriceBetween(BigDecimal from, BigDecimal to);

    Flux<ProductEntity> findAllBy(Pageable pageable);
}
