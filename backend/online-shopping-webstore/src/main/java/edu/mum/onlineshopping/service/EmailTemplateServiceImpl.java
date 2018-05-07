package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Transactional
public class EmailTemplateServiceImpl implements EmailTemplateService {
    private TemplateEngine templateEngine;

    @Autowired
    public EmailTemplateServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public String build(Order order) {
        Context context = new Context();
        double total_before_tax = order.getTotalAmount();
        double tax_amount = total_before_tax * order.getTAX() / 100;
        context.setVariable("customer_name", order.getPerson().getFullName());
        context.setVariable("total_amount_before_tax", total_before_tax);
        context.setVariable("tax", order.getTAX());
        context.setVariable("tax_amount", tax_amount);
        context.setVariable("total_amount_after_tax", total_before_tax + tax_amount);
        context.setVariable("order", order);
        return templateEngine.process("order_email", context);
    }

    @Override
    public String build(Person person) {
        Context context = new Context();
        context.setVariable("user_full_name", person.getFullName());
        return templateEngine.process("register_congrats", context);
    }
}
