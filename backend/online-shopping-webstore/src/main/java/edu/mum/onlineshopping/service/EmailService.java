package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.TinyEmailObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

public interface EmailService {
    void sendSimpleMessage(TinyEmailObject emailObject);
    void sendOrderMessageUsingTemplate(Order order);
    void sendMessageWithAttachment(TinyEmailObject emailObject,
                                   String attachmentFileName,
                                   String pathToAttachment);
}
