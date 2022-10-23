package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.entity.aggregateroot.Order;
import com.food.ordering.system.order.service.domain.entity.aggregateroot.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

import java.util.List;

/**
 * @author kany
 */
public interface OrderDomainService {

    /**
     * validate and initiate order
     * @param order
     * @param restaurant
     * @return
     */
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    /**
     * pay order
     * @param order
     * @return
     */
    OrderPaidEvent payOrder(Order order);


    /**
     * cancel order payment
     * @param order
     * @param failureMessages
     * @return
     */
    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);


    /**
     * cancel Order
     * @param order
     * @param failureMessages
     */
    void cancelOrder(Order order, List<String> failureMessages);

}
