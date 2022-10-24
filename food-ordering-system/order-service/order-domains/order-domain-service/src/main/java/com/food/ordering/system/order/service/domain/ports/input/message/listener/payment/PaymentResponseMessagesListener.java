package com.food.ordering.system.order.service.domain.ports.input.message.listener.payment;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessagesListener {

    /**
     * payment complete
     *
     * @param paymentResponse
     */
    void paymentComplete(PaymentResponse paymentResponse);

    /**
     * payment cancelled
     *
     * @param paymentResponse
     */
    void paymentCancelled(PaymentResponse paymentResponse);
}
