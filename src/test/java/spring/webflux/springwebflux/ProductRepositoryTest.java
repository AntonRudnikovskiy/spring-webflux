package spring.webflux.springwebflux;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import reactor.test.StepVerifier;
import spring.webflux.springwebflux.sec01.repository.ProductRepository;

import java.math.BigDecimal;

class ProductRepositoryTest extends AbstractTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByPriceBetween() {
        productRepository.findByPriceBetween(BigDecimal.valueOf(100), BigDecimal.valueOf(150))
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    void findAllBy() {
        productRepository.findAllBy(PageRequest.of(0, 3).withSort(Sort.by("price").ascending()))
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }
}