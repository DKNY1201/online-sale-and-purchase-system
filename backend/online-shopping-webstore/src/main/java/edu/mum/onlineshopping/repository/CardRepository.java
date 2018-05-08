package edu.mum.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.onlineshopping.domain.Card;


@Repository
public interface CardRepository extends JpaRepository<Card, String>{

}
