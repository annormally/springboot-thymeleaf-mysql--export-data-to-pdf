package springboot.thymeleaf.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.thymeleaf.entity.CompanyEntity;
import springboot.thymeleaf.repository.CompanyEntityRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyService {

    private final CompanyEntityRepository companyEntityRepository;

    public CompanyService(CompanyEntityRepository companyEntityRepository) {
        this.companyEntityRepository = companyEntityRepository;
    }

    public List<CompanyEntity> listAll() {
        return companyEntityRepository.findAll(Sort.by("companyName").ascending());
    }
}
