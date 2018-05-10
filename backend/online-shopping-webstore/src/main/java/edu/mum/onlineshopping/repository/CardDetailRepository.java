package edu.mum.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.onlineshopping.domain.CardDetail;

@Repository
public interface CardDetailRepository extends JpaRepository<CardDetail, Integer>{

}
