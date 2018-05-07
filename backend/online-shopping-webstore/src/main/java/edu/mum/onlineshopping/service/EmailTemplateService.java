package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Person;

public interface EmailTemplateService {
    public String build(Order order);
    public String build(Person person);
}
