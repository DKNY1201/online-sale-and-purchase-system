package edu.mum.pm.controller;

import edu.mum.pm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
public class OrderManagementController {

    @Autowired
    private OrderService orderService;

    @GetMapping({"/"})
    public String order(Model model) {
        model.addAttribute("historicalOrder", orderService.findAll());
        return "admin/order/index";
    }

}
