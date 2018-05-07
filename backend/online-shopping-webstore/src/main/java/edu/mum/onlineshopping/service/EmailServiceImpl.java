package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.TinyEmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private OrderEmailTemplateService emailTBService;

    public void sendSimpleMessage(TinyEmailObject emailObject) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailObject.getTo());
            message.setSubject(emailObject.getSubject());
            message.setText(emailObject.getText());

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void sendOrderMessageUsingTemplate(Order order) {
        MimeMessagePreparator msgPrep = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            String orderedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            messageHelper.setTo(order.getPerson().getEmail());
            messageHelper.setSubject("Order information for " + order.getPerson().getFirstName() + " " + order.getPerson().getLastName() +
                                    " on " + orderedDate);
            String content = emailTBService.build(order);
            messageHelper.setText(content, true);
        };

        try {
            emailSender.send(msgPrep);
        } catch (MailException e) {
            System.out.println("Send email failed " + e.getMessage());
        }
    }

    @Override
    public void sendMessageWithAttachment(TinyEmailObject emailObject,
                                          String attachmentFileName,
                                          String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailObject.getTo());
            helper.setSubject(emailObject.getSubject());
            helper.setText(emailObject.getText());

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment(attachmentFileName, file);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
