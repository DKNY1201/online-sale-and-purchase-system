package mum.edu.pm.service;

import mum.edu.pm.entity.CustomerOrder;
import mum.edu.pm.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public CustomerOrder addOrder(CustomerOrder order) {
        return customerOrderRepository.save(order);
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        return (List<CustomerOrder>) customerOrderRepository.findAll();
    }

    @Override
    public Optional<CustomerOrder> getOrder(long orderId) {
        return customerOrderRepository.findById(orderId);
    }

    @Override
    public void removeOrder(CustomerOrder order) {
        customerOrderRepository.delete(order);
    }
}
