package com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantpproval;

import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {

    /**
     * order approval
     * @param restaurantApprovalResponse
     */
    void orderApproval(RestaurantApprovalResponse restaurantApprovalResponse);

    /**
     * order reject
     * @param restaurantApprovalResponse
     */
    void orderReject(RestaurantApprovalResponse restaurantApprovalResponse);
}
