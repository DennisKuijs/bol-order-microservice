package com.pageupcomputers.bolComWebhookReceiver;

import java.time.LocalDateTime;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.pageupcomputers.bolComWebhookReceiver.Enums.Status;
import com.pageupcomputers.bolComWebhookReceiver.Models.Order;
import com.pageupcomputers.bolComWebhookReceiver.Repositories.OrderRepository;

@DataJpaTest
/**
 * Create a test database
 */
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderRepositoryTests {
    
    /**
     * Inject OrderRepository for communicating with the (test) database table processed_orders
     */
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void ProcessedOrderRepository_Save_ReturnSavedProcessedOrder() {
        
        /**
         * Arrange
         */
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setOrderId("234561");
        order.setOrderPlaced(LocalDateTime.now());
        order.setOrderProcessed(LocalDateTime.now());
        order.setCountry("NL");
        order.setStatus(Status.OPEN);
        order.setInvoiceNumber("VKF1234");
        order.setInvoiceSentByEmail(false);
        order.setInvoiceUploadedOnMarketplace(false);
        
        /**
         * Act
         */
        Order savedOrder = orderRepository.save(order);
        
        /**
         * Assert
         */
        Assertions.assertThat(savedOrder).isNotNull();
        Assertions.assertThat(savedOrder.getId()).isNotNull();
    }
}
