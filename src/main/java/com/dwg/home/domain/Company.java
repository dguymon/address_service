package com.dwg.home.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Company entity class.
 * 
 * @author Danazn
 *
 */
@Entity
@Table(name = "COMPANIES")
@EntityListeners(AuditingEntityListener.class)
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "company_id", 
          updatable = false, 
          nullable = false)
  private Long companyId;

  @Column(name = "company_name")
  @NotNull
  private String companyName;

  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
}