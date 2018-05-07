package edu.mum.onlineshopping.domain;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private String dateFrom;
    private String dateTo;

    private String email;
    private String reportExcelFile;

    public String getReportExcelFile() {
        return reportExcelFile;
    }

    public void setReportExcelFile(String reportExcelFile) {
        this.reportExcelFile = reportExcelFile;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    private List<Order> orders = new ArrayList<>();

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
