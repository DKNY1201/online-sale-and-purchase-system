package edu.mum.pm.restapi.controller;

import edu.mum.pm.domain.CustomerOrder;
import edu.mum.pm.domain.OrderDetail;
import edu.mum.pm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/order")
public class OrderRestController {

    @Autowired
    private OrderService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody CustomerOrder customerOrder) {
        for (OrderDetail orderDetail : customerOrder.getOrderDetails()) {
            orderDetail.setCustomerOrder(customerOrder);
        }
        return new ResponseEntity<CustomerOrder>(service.save(customerOrder), HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        return new ResponseEntity<List<CustomerOrder>>(service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        CustomerOrder customerOrder = service.findById(id);
        customerOrder.clearOrderDetails();
        service.delete(customerOrder);
        return new ResponseEntity<CustomerOrder>(customerOrder, HttpStatus.OK);
    }

}
