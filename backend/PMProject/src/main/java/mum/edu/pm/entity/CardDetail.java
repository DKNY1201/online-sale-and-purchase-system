package mum.edu.pm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CardDetail {
	@Id
	@GeneratedValue
	private Long id;
	private String cardHolderName;
	private String cardType;
	private String cardNumber; // Long
	private Date cardExpirationDate;
	
	public CardDetail() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
	
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	public String getCardType() {
		return cardType;
	}
	
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public Date getCardExpirationDate() {
		return cardExpirationDate;
	}
	
	public void setCardExpirationDate(Date cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}
	
}
