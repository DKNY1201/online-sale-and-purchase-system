package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.util.GenerateReport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

	@Override
	public void sendReport() {
		try {
			GenerateReport.saleReport();
		} catch(Exception e) {
			System.out.println("Can't generate or send email. Error: " + e.getMessage());
		}

	}
}
