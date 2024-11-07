package com.pageupcomputers.bolComWebhookReceiver;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.pageupcomputers.bolComWebhookReceiver.DTO.BillingDetailsDTO;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrderDetailsDTO;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrderItemsDTO;
import com.pageupcomputers.bolComWebhookReceiver.DTO.ProductDTO;
import com.pageupcomputers.bolComWebhookReceiver.Enums.Status;
import com.pageupcomputers.bolComWebhookReceiver.Models.Order;
import com.pageupcomputers.bolComWebhookReceiver.Repositories.OrderRepository;
import com.pageupcomputers.bolComWebhookReceiver.Services.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {
    
    /**
     * Create a mock instance for the OrderRepository
     */
    @Mock
    private OrderRepository orderRepository;

    //Create a mock instance for the RabbitMQ Connection
    @Mock
    private RabbitTemplate rabbitTemplate;

    /**
     * Inject the OrderService as a mock instance
     */
    @InjectMocks
    private OrderService orderService;

    @Test
    public void ProcessedOrderService_QueueOrder_ReturnsSavedProcessedOrder() {
        
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
        
        BillingDetailsDTO billingDetailsDTO = new BillingDetailsDTO();
        billingDetailsDTO.setSalutation("Male");
        billingDetailsDTO.setFirstName("Dennis");
        billingDetailsDTO.setSurname("Kuijs");
        billingDetailsDTO.setStreetname("Schubertstraat");
        billingDetailsDTO.setHouseNumber("25");
        billingDetailsDTO.setZipCode("5914BL");
        billingDetailsDTO.setCity("VENLO");
        billingDetailsDTO.setCountryCode("NL");
        billingDetailsDTO.setEmail("Dennis@denniskuijs.nl");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setEan("0193808062490");
        productDTO.setTitle("HP 301 - Inktcartridge / Normale Capaciteit / Kleur");

        OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
        orderItemsDTO.setProduct(productDTO);
        orderItemsDTO.setQuantity(5);
        orderItemsDTO.setQuantityCancelled(0);
        orderItemsDTO.setQuantityShipped(0);
        orderItemsDTO.setUnitPrice(new BigDecimal(4.50));
        orderItemsDTO.setCommission(new BigDecimal(4.50));

        List<OrderItemsDTO> orderItemsList = new ArrayList<OrderItemsDTO>();
        orderItemsList.add(orderItemsDTO);

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        orderDetailsDTO.setOrderId("123456");
        orderDetailsDTO.setOrderPlacedDateTime("2023-09-07T11:53:55+02:00");
        orderDetailsDTO.setBillingDetails(billingDetailsDTO);
        orderDetailsDTO.setOrderItems(orderItemsList);

        /**
         * Act
         */
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Order savedOrder = orderService.queueOrder(orderDetailsDTO);

        /**
         * Assert
         */
        Assertions.assertThat(savedOrder).isNotNull();

    }
}
