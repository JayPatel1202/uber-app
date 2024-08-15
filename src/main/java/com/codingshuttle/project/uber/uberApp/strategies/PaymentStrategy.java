package com.codingshuttle.project.uber.uberApp.strategies;

import com.codingshuttle.project.uber.uberApp.entities.Payment;
import com.codingshuttle.project.uber.uberApp.entities.enums.PaymentStatus;

public interface PaymentStrategy {

    Double PLATFORM_COMMISSION = 0.3;

    void processPayment(Payment payment);


}
