package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessagesListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class PaymentResponseMessagesListenerImpl implements PaymentResponseMessagesListener {
    @Override
    public void paymentComplete(PaymentResponse paymentResponse) {

    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {

    }
}
