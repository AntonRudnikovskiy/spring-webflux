package spring.webflux.springwebflux.sec01.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table
public class CustomerOrderEntity {
    @Id
    private UUID orderId;
    private Long customerId;
    private Long productId;
    private Long amount;
    private Instant orderDate;
}
