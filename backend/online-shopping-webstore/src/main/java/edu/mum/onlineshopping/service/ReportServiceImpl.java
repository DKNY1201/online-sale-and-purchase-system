package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Orderline;
import edu.mum.onlineshopping.domain.Report;
import edu.mum.onlineshopping.repository.OrderRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OrderRepository orderRepository;
    private static List<String> reportColums = new ArrayList<>();

    static {
        reportColums.add("ORDER_ID");
        reportColums.add("ORDER_DATE");
        reportColums.add("PRODUCT_NAME");
        reportColums.add("PRODUCT_PRICE");
        reportColums.add("QUANTITY");
    }

    private File generateSaleReport(String reportExcelFileName, List<Order> orders) {
        if (orders == null) return null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(reportExcelFileName);

        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        for (int col = 0; col < reportColums.size(); ++col) {
            cell = row.createCell(col + 1);
            cell.setCellValue(reportColums.get(col));
        }

        int ithRow = 2;
        double sum = 0.0;

        for (Order o : orders) {
            for (Orderline ol : o.getOrderLines()) {
                row = spreadsheet.createRow(ithRow++);

                cell = row.createCell(1);
                cell.setCellValue(o.getId());
                cell = row.createCell(2);
                cell.setCellValue(o.getOrderDate());
                cell = row.createCell(3);
                cell.setCellValue(ol.getProduct().getProductName());
                cell = row.createCell(4);
                cell.setCellValue(ol.getProduct().getPrice());
                cell = row.createCell(5);
                cell.setCellValue(ol.getQuantity());
                sum += ol.getProduct().getPrice() * ol.getQuantity();
            }
        }

        XSSFRow row_sum = spreadsheet.createRow(ithRow);
        cell = row_sum.createCell(0);
        cell.setCellValue("SUMMARY");
        cell = row_sum.createCell(5);
        cell.setCellValue(sum);


        File file = new File(reportExcelFileName);
        FileOutputStream out = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            workbook.write(out);
            System.out.println(reportExcelFileName + " generated successfully");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
        finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e){

                }
            }
        }

        return file;
    }

    @Override
    public void generateReport(Report report) {
        if (null == report) {
            return;
        }
        try {
            String reportExcelFileName = "SaleReport" + System.currentTimeMillis() + ".xlsx";
            File rFile = generateSaleReport(reportExcelFileName, report.getOrders());
            if (null != rFile) {
                report.setReportExcelFile(rFile.getAbsolutePath());
            } else {
                System.out.println("Can't generate report excel file");
                report.setReportExcelFile(null);
            }
        } catch(Exception e) {
            System.out.println("Can't generate or send email. Error: " + e.getMessage());
        }

    }
}
