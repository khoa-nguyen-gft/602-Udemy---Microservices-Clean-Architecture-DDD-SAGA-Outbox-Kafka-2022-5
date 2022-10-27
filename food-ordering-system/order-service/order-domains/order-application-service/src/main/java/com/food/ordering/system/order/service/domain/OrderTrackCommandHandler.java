package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.entity.aggregateroot.Order;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author kany
 */
@Slf4j
@Component
public class OrderTrackCommandHandler {

    private final OrderRepository orderRepository;

    private final OrderDataMapper orderDataMapper;

    public OrderTrackCommandHandler(OrderRepository orderRepository, OrderDataMapper orderDataMapper) {
        this.orderRepository = orderRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        Optional<Order> orderOptional = orderRepository.findByTrackingId(new TrackingId(query.getOrderTrackingId()));

        if (orderOptional.isEmpty()) {
            log.warn("Could not find order with track id {}", query.getOrderTrackingId());
            throw new OrderNotFoundException("Could not find order with tracking id " + query.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderOptional.get());
    }
}
