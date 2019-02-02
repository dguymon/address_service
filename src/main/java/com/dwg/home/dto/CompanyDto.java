package com.dwg.home.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.Size;

/**
 * DTO version of the Company domain class.
 * 
 * @author Danazn
 *
 */
@JsonPropertyOrder({"company_id", "company_name"})
public class CompanyDto {

  @JsonProperty("company_id")
  private Long companyId;

  @JsonProperty("company_name")
  @Size(max = 100)
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