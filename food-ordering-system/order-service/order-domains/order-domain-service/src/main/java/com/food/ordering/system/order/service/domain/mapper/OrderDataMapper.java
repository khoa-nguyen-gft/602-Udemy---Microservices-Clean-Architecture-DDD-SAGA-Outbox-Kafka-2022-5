package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.dto.OrderAddress;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.aggregateroot.Order;
import com.food.ordering.system.order.service.domain.entity.aggregateroot.Restaurant;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author kany
 */
@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand command) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(command.getRestaurantId()))
                .products(command.getItems().stream().map(item ->
                        new Product(new ProductId(item.getProductId()))
                ).collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand command) {
        return Order.builder()
                .customerId(new CustomerId(command.getCustomerId()))
                .restaurantId(new RestaurantId(command.getRestaurantId()))
                .streetAddress(createOrderAddressToStreetAddress(command.getOrderAddress()))
                .price(new Money(command.getPrice()))
                .items(createOrderItemCommandToItemEntities(command.getItems()))
                .build();
    }

    private List<OrderItem> createOrderItemCommandToItemEntities(
            List<com.food.ordering.system.order.service.domain.dto.dto.OrderItem> items) {
        return items.stream().map(dto ->
                OrderItem.builder()
                        .product(new Product(new ProductId(dto.getProductId())))
                        .price(new Money(dto.getPrice()))
                        .quantity(dto.getQuantity())
                        .subTotal(new Money(dto.getSubTotal()))
                        .build()
        ).collect(Collectors.toList());
    }

    private StreetAddress createOrderAddressToStreetAddress(OrderAddress orderAddress) {
        return StreetAddress.builder()
                .id(UUID.randomUUID())
                .street(orderAddress.getStreet())
                .postalCode(orderAddress.getPostalCode())
                .city(orderAddress.getCity())
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }
}
