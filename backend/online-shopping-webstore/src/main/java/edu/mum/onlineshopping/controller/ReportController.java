package edu.mum.onlineshopping.controller;

import edu.mum.onlineshopping.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequestMapping("admin/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String generateReport() {
        reportService.sendReport();
        return "admin/user/index";
    }
}
