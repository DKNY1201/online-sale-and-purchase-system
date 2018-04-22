package mum.edu.pm.service;

import mum.edu.pm.entity.Payment;
import mum.edu.pm.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return (List<Payment>) paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPayment(long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public void removePayment(Payment payment) {
        paymentRepository.delete(payment);
    }
}
