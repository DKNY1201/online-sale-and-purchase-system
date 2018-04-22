package mum.edu.pm.controller;

import mum.edu.pm.service.CustomerOrderService;
import mum.edu.pm.service.IUserService;
import mum.edu.pm.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createOrder")
    @PreAuthorize(value = "hasAnyRole('ROLE_CUSTOMER')")
    public String createOrder() {
        return null;
    }
}
