package edu.mum.onlineshopping.controller;

import edu.mum.onlineshopping.config.SessionListener;
import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.domain.Report;
import edu.mum.onlineshopping.service.OrderService;
import edu.mum.onlineshopping.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;

@Controller()
@RequestMapping("admin/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SessionListener session;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String loadOrders(Model model, @ModelAttribute("report") Report report) {
        Person person = session.getPerson();
        if (person != null) {
            if (person.getEmail() != null && !person.getEmail().isEmpty()) {
                report.setEmail(person.getEmail());
            }
        }
        model.addAttribute("historicalOrder", report.getOrders());
        return "admin/report/order";
    }

    @RequestMapping(params = "search", value="/", method= RequestMethod.POST)
    public String search(Model model, @ModelAttribute("report") Report report) {
        if (report.getDateFrom().isEmpty() ||
                report.getDateTo().isEmpty())
            report.setOrders(orderService.findAll());
        else
            report.setOrders(orderService.findByDate(Date.valueOf(report.getDateFrom()), Date.valueOf(report.getDateTo())));

        model.addAttribute("historicalOrder",report.getOrders());
        return "admin/report/order";
    }

    @RequestMapping(params = "send_report", value="/", method= RequestMethod.POST)
    public String report(Model model, @ModelAttribute("report") Report report) {
        model.addAttribute("historicalOrder", report.getOrders());
        reportService.sendReport(report);
        return "admin/report/order";
    }
}
