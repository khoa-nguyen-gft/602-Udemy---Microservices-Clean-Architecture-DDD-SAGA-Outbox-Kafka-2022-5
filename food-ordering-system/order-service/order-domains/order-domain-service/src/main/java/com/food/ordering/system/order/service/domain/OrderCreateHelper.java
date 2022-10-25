package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.entity.aggregateroot.Customer;
import com.food.ordering.system.order.service.domain.entity.aggregateroot.Order;
import com.food.ordering.system.order.service.domain.entity.aggregateroot.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateHelper {

    private final OrderDomainService orderDomainService;

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final RestaurantRepository restaurantRepository;

    private final OrderDataMapper orderDataMapper;

    public OrderCreateHelper(OrderDomainService orderDomainService,
                             OrderRepository orderRepository,
                             CustomerRepository customerRepository,
                             RestaurantRepository restaurantRepository,
                             OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand request) {
        checkCustomer(request.getCustomerId());
        Restaurant restaurant = checkRestaurant(request);
        Order order = orderDataMapper.createOrderCommandToOrder(request);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order is create with order id: {}", orderCreatedEvent.getOrder().getId().getValue());

        return orderCreatedEvent;
    }


    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.warn("");
            throw new OrderDomainException("could not save order");
        }

        log.info("Order is save with id: {}", orderResult.getId().getValue());
        return orderResult;
    }

    private Restaurant checkRestaurant(CreateOrderCommand command) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(command);
        Optional<Restaurant> restaurantInformation = restaurantRepository.findRestaurantInformation(restaurant);
        if (restaurantInformation.isEmpty()) {
            log.warn("could not find restaurant with restaurant Id: " + command.getRestaurantId());
            throw new OrderDomainException("could not find restaurant with restaurant Id: " + command.getRestaurantId());
        }
        return restaurantInformation.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("could not find customer with customer Id: " + customerId);
            throw new OrderDomainException("could not find customer with customer Id: " + customerId);
        }
    }
}
