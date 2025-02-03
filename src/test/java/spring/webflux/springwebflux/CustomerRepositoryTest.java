package spring.webflux.springwebflux;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;
import spring.webflux.springwebflux.sec01.entity.CustomerEntity;
import spring.webflux.springwebflux.sec01.repository.CustomerRepository;

public class CustomerRepositoryTest extends AbstractTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void findAll() {
        repository.findAll()
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .expectNextCount(10)
                .expectComplete()
                .verify();
    }

    @Test
    public void findById() {
        repository.findById(2L)
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByName() {
        repository.findByName("jake")
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("jake@gmail.com", c.getEmail()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findAllByName() {
        repository.findByEmailEndingWith("ke@gmail.com")
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike@gmail.com", c.getEmail()))
                .assertNext(c -> Assertions.assertEquals("jake@gmail.com", c.getEmail()))
                .expectComplete()
                .verify();
    }

    @Test
    public void insertAndDeleteCustomer() {
        // insert
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail("marshal");
        customer.setName("marshal@gmail.com");
        repository.save(customer)
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertNotNull(c.getId()))
                .expectComplete()
                .verify();

        // count
        repository.count()
                .as(StepVerifier::create)
                .expectNext(11L)
                .expectComplete()
                .verify();

        // delete
        repository.deleteById(11L)
                .then(repository.count())
                .as(StepVerifier::create)
                .expectNext(10L)
                .expectComplete()
                .verify();
    }

    @Test
    public void updateCustomer() {
        repository.findByName("jake")
                .doOnNext(c -> c.setName("noel"))
                .flatMap(c -> repository.save(c))
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("jake", c.getName()))
                .expectComplete()
                .verify();
    }

}
