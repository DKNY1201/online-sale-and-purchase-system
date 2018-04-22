package mum.edu.pm.repository;

import mum.edu.pm.entity.CustomerOrder;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {

}
