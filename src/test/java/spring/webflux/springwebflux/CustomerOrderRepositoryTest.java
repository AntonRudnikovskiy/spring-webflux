package spring.webflux.springwebflux;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;
import spring.webflux.springwebflux.sec01.repository.CustomerOrderRepository;

class CustomerOrderRepositoryTest extends AbstractTest {

    @Autowired
    private CustomerOrderRepository repository;

    @Test
    void getProductsOrderedByCustomer() {
        repository.getProductsOrderedByCustomer("mike")
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }

    @Test
    void getOrderDetailsByProduct() {
        repository.getOrderDetailsByProduct("iphone 20")
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }
}