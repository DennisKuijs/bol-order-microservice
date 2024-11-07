package com.pageupcomputers.bolComWebhookReceiver.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pageupcomputers.bolComWebhookReceiver.Models.Order;

/**
 * Repository for communicating with the Order model in the database (processed_orders table)
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    /**
     * Check if OrderId already exists in the database table
     * @param orderId
     * @return Boolean which indicates if the order exists in the database
     */
    Boolean existsByOrderId(String orderId);
}
