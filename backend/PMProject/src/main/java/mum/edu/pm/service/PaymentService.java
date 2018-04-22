package mum.edu.pm.service;

import mum.edu.pm.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment addPayment(Payment payment);

    List<Payment> getAllPayments();

    Optional<Payment> getPayment(long paymentId);

    void removePayment(Payment payment);
}
