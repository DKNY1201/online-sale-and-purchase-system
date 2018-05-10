package edu.mum.onlineshopping.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.onlineshopping.domain.Payment;
import edu.mum.onlineshopping.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Override
	public Payment save(Payment payment) {
		// TODO Auto-generated method stub
		return paymentRepo.save(payment);
	}

}
