package com.food.ordering.system.order.service.domain.ports;

import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author kany
 */
@Slf4j
@Component
public class OrderTrackCommandHandler {

    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        return null;
    }
}
