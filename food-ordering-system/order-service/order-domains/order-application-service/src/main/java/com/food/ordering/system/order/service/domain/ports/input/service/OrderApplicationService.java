package com.food.ordering.system.order.service.domain.ports.input.service;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

/**
 * @author kany
 */
public interface OrderApplicationService {

    /**
     * create other
     *
     * @param command
     * @return
     */
    CreateOrderResponse createOrder(@Valid CreateOrderCommand command);

    /**
     * track order
     *
     * @param query
     * @return
     */
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery query);

}
