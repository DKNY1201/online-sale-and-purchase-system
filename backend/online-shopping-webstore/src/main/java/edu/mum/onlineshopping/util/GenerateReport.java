package edu.mum.onlineshopping.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenerateReport {
    public static void saleReport()throws Exception{

        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_onlineshopping" ,
                "root" ,
                ""
        );
        if (connect != null) {
            System.out.println("Connected to the database test1");
        }
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT orderline.id,order_table.order_date,product.product_name,product.price,orderline.quantity "
                + "FROM orderline,order_table,product "
                + "WHERE (orderline.order_id = order_table.id && orderline.product_id = product.id)");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("sale report");

        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("ORDER_ID");
        cell = row.createCell(2);
        cell.setCellValue("ORDER_DATE");
        cell = row.createCell(3);
        cell.setCellValue("PRODUCT_NAME");
        cell = row.createCell(4);
        cell.setCellValue("PRODUCT_PRICE");
        cell = row.createCell(5);
        cell.setCellValue("QUANTITY");
        int i = 2;
        long sum = 0;
        while(resultSet.next()) {
            row = spreadsheet.createRow(i);
            cell = row.createCell(1);
            //System.out.println(resultSet.getInt("EMP_ID"));
            cell.setCellValue(resultSet.getInt("id"));
            cell = row.createCell(2);
            cell.setCellValue(resultSet.getString("order_date"));
            cell = row.createCell(3);
            cell.setCellValue(resultSet.getString("product_name"));
            cell = row.createCell(4);
            cell.setCellValue(resultSet.getInt("price"));
            cell = row.createCell(5);
            cell.setCellValue(resultSet.getInt("quantity"));
            i++;
            sum+=resultSet.getInt("price")*resultSet.getInt("quantity");
        }
        XSSFRow row_sum = spreadsheet.createRow(i);
        cell = row_sum.createCell(0);
        cell.setCellValue("SUMMARY");
        cell = row_sum.createCell(5);
        cell.setCellValue(sum);


        File file = new File("sale_report.xlsx");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }}
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        System.out.println("exceldatabase.xlsx written successfully");

        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "hadesvu";
        String password = "wearestrong";

        String mailTo = "quytran288@gmail.com";
        String subject = "test mail";
        String message = "I have some attachments for you.";
	     try {
	          sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
	              subject, message, file);
	          System.out.println("Email sent.");
	      } catch (Exception ex) {
	          System.out.println("Could not send email.");
	          ex.printStackTrace();
	      }
    }


    public static void sendEmailWithAttachments(String host, String port,
                                                final String userName, final String password, String toAddress,
                                                String subject, String message, File out)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        if (out != null ) {

            MimeBodyPart attachPart = new MimeBodyPart();

            try {
                attachPart.attachFile(out);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            multipart.addBodyPart(attachPart);

        }

        // sets the multi-part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);

    }

    public static void main(String[] args) throws Exception  {
        saleReport();
    }
}
