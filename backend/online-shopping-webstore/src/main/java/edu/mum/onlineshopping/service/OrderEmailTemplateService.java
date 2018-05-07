package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Order;

public interface OrderEmailTemplateService {
    public String build(Order order);
}
