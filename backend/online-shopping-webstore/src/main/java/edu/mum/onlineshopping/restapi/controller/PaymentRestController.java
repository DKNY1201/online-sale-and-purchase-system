package edu.mum.onlineshopping.restapi.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.onlineshopping.domain.Card;
import edu.mum.onlineshopping.domain.CardDetail;
import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Orderline;
import edu.mum.onlineshopping.domain.Transaction;
import edu.mum.onlineshopping.repository.CardRepository;
import edu.mum.onlineshopping.repository.TransactionRepository;


@RestController
@RequestMapping("/api/payment")
public class PaymentRestController {
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	TransactionRepository transRepo;
	
	@GetMapping("/get")
	public List<Card> listCard() {
		System.out.println("Test");
		return cardRepo.findAll();
	}
	
	@GetMapping("/authorize/{id}/{value}")
	public String getCardById(@PathVariable("id") String id, @PathVariable("value") double paymentAmount) {
		Card card = cardRepo.findOne(id);
		if(card == null) {
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
	
	@RequestMapping(value="/check", method=RequestMethod.POST)
	public String create(HttpServletRequest request) {
		String cardNumber = request.getParameter("cardNumber");
		String expiryYear = request.getParameter("expiryYear");
		String expiryMonth = request.getParameter("expiryMonth");
		String cardHolderName = request.getParameter("cardHolderName");
		String ccv = request.getParameter("ccv");
		double paymentAmount = Double.parseDouble(request.getParameter("paymentAmount"));
		Card card = cardRepo.findOne(cardNumber);
		if(card == null) {
			return "Invalid card";
		} else {
			if (!card.getCardHolderName().equals(cardHolderName) || !card.getCcvNumber().equals(ccv) || 
					!card.getExpiryMonth().equals(expiryMonth) || !card.getExpiryYear().equals(expiryYear)) {
				return "Invalid card";
			}
		}
		double availableAmount = card.getValue();
		Transaction trans = new Transaction();
		trans.setActive(false);
		trans.setAvailableAmount(availableAmount);
		trans.setCardNumber(card);
		trans.setTransactionAmount(paymentAmount);
		transRepo.save(trans);
		if (paymentAmount > availableAmount) {
			return "Your balance is not enough to pay this order";
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
