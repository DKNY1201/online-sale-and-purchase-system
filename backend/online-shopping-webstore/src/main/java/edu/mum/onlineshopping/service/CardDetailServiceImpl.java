package edu.mum.onlineshopping.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.onlineshopping.domain.CardDetail;
import edu.mum.onlineshopping.repository.CardDetailRepository;

@Service
@Transactional
public class CardDetailServiceImpl implements CardDetailService{
	@Autowired
	private CardDetailRepository cardDetailRepo;

	@Override
	public CardDetail save(CardDetail cardDetail) {
		// TODO Auto-generated method stub
		return cardDetailRepo.save(cardDetail);
	}

}
