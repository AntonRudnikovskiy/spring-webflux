package spring.webflux.springwebflux.sec01.dto;

import java.time.Instant;
import java.util.UUID;

public record OrderDetails(UUID orderId,
                           String productName,
                           Long amount,
                           Instant orderDate) {
}
