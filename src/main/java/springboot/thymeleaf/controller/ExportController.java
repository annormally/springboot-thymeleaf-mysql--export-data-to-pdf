package springboot.thymeleaf.controller;

import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.thymeleaf.entity.CompanyEntity;
import springboot.thymeleaf.export.CompanyExport;
import springboot.thymeleaf.service.CompanyService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ExportController {

    private final CompanyService companyService;

    public ExportController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/export/company")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=company_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<CompanyEntity> companyList = companyService.listAll();

        CompanyExport companyExport = new CompanyExport(companyList);
        companyExport.export(response);
    }
}
