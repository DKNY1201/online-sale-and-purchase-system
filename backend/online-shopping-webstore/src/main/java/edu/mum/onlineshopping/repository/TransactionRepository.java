package edu.mum.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.onlineshopping.domain.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
