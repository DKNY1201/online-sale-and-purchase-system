package mum.edu.pm.controller;

import mum.edu.pm.entity.CustomerOrder;
import mum.edu.pm.entity.Payment;
import mum.edu.pm.service.CustomerOrderService;
import mum.edu.pm.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/customer/")
    @PreAuthorize(value = "hasAnyRole('ROLE_CUSTOMER')")
    public String createOrder(@PathVariable("paymentId") String paymentId) {

        Optional<Payment> payment = paymentService.getPayment(Long.valueOf(paymentId));
        CustomerOrder customerOrder = new CustomerOrder();
        return null;
    }
}
