package spring.webflux.springwebflux.sec01.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import spring.webflux.springwebflux.sec01.dto.OrderDetails;
import spring.webflux.springwebflux.sec01.entity.CustomerOrderEntity;
import spring.webflux.springwebflux.sec01.entity.ProductEntity;

import java.util.UUID;

@Repository
public interface CustomerOrderRepository extends ReactiveCrudRepository<CustomerOrderEntity, UUID> {

    @Query(value = """
            SELECT
                p.*
            FROM
                customer c
            INNER JOIN customer_order co ON c.id = co.customer_id
            INNER JOIN product p ON co.product_id = p.id
            WHERE
                c.name = :name
            """)
    Flux<ProductEntity> getProductsOrderedByCustomer(String name);

    @Query(value = """
            SELECT
                co.order_id,
                c.name AS customer_name,
                p.description AS product_name,
                co.amount,
                co.order_date
            FROM
                customer c
            INNER JOIN customer_order co ON c.id = co.customer_id
            INNER JOIN product p ON p.id = co.product_id
            WHERE
                p.description = :description
            ORDER BY co.amount DESC
            """)
    Flux<OrderDetails> getOrderDetailsByProduct(String description);
}
