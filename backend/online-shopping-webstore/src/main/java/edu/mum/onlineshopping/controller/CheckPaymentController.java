package edu.mum.onlineshopping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.onlineshopping.domain.Card;
import edu.mum.onlineshopping.domain.Transaction;
import edu.mum.onlineshopping.repository.CardRepository;
import edu.mum.onlineshopping.repository.TransactionRepository;


@Controller
@RequestMapping("/payment")
public class CheckPaymentController {
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	TransactionRepository transRepo;
	
	@GetMapping("/api")
	public List<Card> listCard() {
		System.out.println("Test");
		return cardRepo.findAll();
	}
	
	@GetMapping("/authorize/{id}/{value}")
	public String getCardById(@PathVariable("id") String id, @PathVariable("value") double paymentAmount) {
		Card card = cardRepo.findOne(id);
		if(card != null) {
			return "No valid card";
		}
		double availableAmount = card.getValue();
		Transaction trans = new Transaction();
		trans.setActive(false);
		trans.setAvailableAmount(availableAmount);
		trans.setCardNumber(card);
		trans.setTransactionAmount(paymentAmount);
		transRepo.save(trans);
		if (paymentAmount > availableAmount) {
			return "No valid number";
		}
		else {
			trans.setActive(true);
			transRepo.save(trans);
			card.setValue(availableAmount - paymentAmount);
			cardRepo.save(card);
		}
		return "Success";
	}
	
	
}
