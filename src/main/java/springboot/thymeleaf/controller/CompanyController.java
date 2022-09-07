package springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springboot.thymeleaf.entity.CompanyEntity;
import springboot.thymeleaf.model.CompanyModel;
import springboot.thymeleaf.repository.CompanyEntityRepository;
import springboot.thymeleaf.service.CompanyService;

@Controller
public class CompanyController {

    private final CompanyEntityRepository companyEntityRepository;

    private final CompanyService companyService;

    public CompanyController(CompanyEntityRepository companyEntityRepository, CompanyService companyService) {
        this.companyEntityRepository = companyEntityRepository;
        this.companyService = companyService;
    }

    @GetMapping("/")
    public ModelAndView getCompanyFields() {
        ModelAndView modelAndView = new ModelAndView("company-form");
        modelAndView.addObject("companyFields", new CompanyModel());
        return modelAndView;
    }

    @GetMapping("/companies")
    public ModelAndView getCompanies() {
        ModelAndView modelAndView = new ModelAndView("companies");
        modelAndView.addObject("companyList", companyService.listAll());
        return modelAndView;
    }


    @PostMapping("/companyFields")
    public ModelAndView setCompanyFields(@ModelAttribute("companyFields") CompanyModel companyModel) {
        ModelAndView modelAndView = new ModelAndView("company-form");
        CompanyEntity companyEntity;
        if (companyModel.getId() != null) {
            companyEntity = companyEntityRepository.findById(companyModel.getId()).get();
        } else {
            companyEntity = new CompanyEntity();
        }

        companyEntity.setCompanyName(companyModel.getCompanyName());
        companyEntity.setCompanyEmail(companyModel.getCompanyEmail());
        companyEntity.setCompanyPhone(companyModel.getCompanyPhone());

        companyEntityRepository.save(companyEntity);
        return modelAndView;
    }
}
