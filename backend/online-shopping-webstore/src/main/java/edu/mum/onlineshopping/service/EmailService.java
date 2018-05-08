package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.domain.TinyEmailObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

public interface EmailService {
    boolean sendSimpleMessage(TinyEmailObject emailObject);
    boolean sendOrderMessageUsingTemplate(Order order);
    boolean sendUserRegisterMessageUsingTemplate(Person person);
    boolean sendMessageWithAttachment(TinyEmailObject emailObject,
                                   String attachmentFileName,
                                   String pathToAttachment);
}
