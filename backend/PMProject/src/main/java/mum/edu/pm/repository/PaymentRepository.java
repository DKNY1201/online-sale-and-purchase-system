package mum.edu.pm.repository;

import mum.edu.pm.entity.Payment;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
