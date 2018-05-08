package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Person;
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
    private EmailTemplateService emailTBService;

    public boolean sendSimpleMessage(TinyEmailObject emailObject) {
        if (emailObject == null) return false;
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailObject.getTo());
            message.setSubject(emailObject.getSubject());
            message.setText(emailObject.getText());

            emailSender.send(message);
            return true;
        } catch (MailException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendOrderMessageUsingTemplate(Order order) {
        if(order == null) return false;
        MimeMessagePreparator msgPrep = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            String orderedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            messageHelper.setTo(order.getPerson().getEmail());
            messageHelper.setSubject("Order information for " + order.getPerson().getFullName() + " on " + orderedDate);
            String content = emailTBService.build(order);
            messageHelper.setText(content, true);
        };

        try {
            emailSender.send(msgPrep);
            return true;
        } catch (MailException e) {
            System.out.println("Send email failed " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sendUserRegisterMessageUsingTemplate(Person person) {
        if(person == null) return false;
        MimeMessagePreparator msgPrep = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(person.getEmail());
            messageHelper.setSubject("Online Shopphing Webstore Registration System");
            String content = emailTBService.build(person);
            messageHelper.setText(content, true);
        };

        try {
            emailSender.send(msgPrep);
            return true;
        } catch (MailException e) {
            System.out.println("Send email failed " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sendMessageWithAttachment(TinyEmailObject emailObject,
                                          String attachmentFileName,
                                          String pathToAttachment) {
        if(emailObject == null || attachmentFileName == null || pathToAttachment == null) return false;
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
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
