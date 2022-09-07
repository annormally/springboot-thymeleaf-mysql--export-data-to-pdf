package springboot.thymeleaf.entity;

import javax.persistence.*;

@Entity
@Table(name = "company_table")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "companyEmail")
    private String companyEmail;

    @Column(name = "companyPhone")
    private Integer companyPhone;

    public CompanyEntity() {
    }

    public CompanyEntity(Long id, String companyName, String companyEmail, Integer companyPhone) {
        this.id = id;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Integer getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(Integer companyPhone) {
        this.companyPhone = companyPhone;
    }
}
