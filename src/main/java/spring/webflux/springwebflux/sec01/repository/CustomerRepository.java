package spring.webflux.springwebflux.sec01.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import spring.webflux.springwebflux.sec01.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<CustomerEntity, Long> {

    Flux<CustomerEntity> findByName(String name);

    Flux<CustomerEntity> findByEmailEndingWith(String email);
}
