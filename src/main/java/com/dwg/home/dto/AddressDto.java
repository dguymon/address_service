package com.dwg.home.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

/**
 * DTO for the Address domain object.
 * 
 * @author Danazn
 *
 */
@JsonPropertyOrder({"addressId", "person_dto", "company_dto", 
    "po_box", "street_address", "apartment_number", 
    "city", "state", "zipcode", "country"
})
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class AddressDto extends ResourceSupport {

  @JsonProperty("address_id")
  private Long addressId;

  @JsonProperty("person_dto")
  private PersonDto personDto;

  @JsonProperty("companay_dto")
  private CompanyDto companyDto;

  @JsonProperty("po_box")
  @Size(max = 50)
  private String poBox;

  @JsonProperty("street_address")
  @Size(max = 100)
  private String streetAddress;

  @JsonProperty("apartment_number")
  @Size(max = 10)
  private String apartmentNumber;

  @JsonProperty("city")
  @Size(max = 50)
  @NotBlank
  private String city;

  @JsonProperty("state")
  @Size(max = 45)
  private String state;

  @JsonProperty("zipcode")
  @Size(max = 25)
  private String zipcode;

  @JsonProperty("country")
  @Size(max = 50)
  @NotBlank
  private String country;

  @JsonProperty("createdAt")
  private Date createdAt;

  @JsonProperty("updatedAt")
  private Date updatedAt;

  /**
   * Full signature constructor for AddressDto.
   * 
   * @param addressId Long id of address record.
   * @param personDto Embedded PersonDto associated with the address record.
   * @param companyDto Embedded CompanyDto associated with the address record.
   * @param poBox String P.O. Box.
   * @param streetAddress String street address.
   * @param city String city.
   * @param state String state.
   * @param zipcode String zip code.
   * @param country String country.
   */
  @JsonCreator
  public AddressDto(@JsonProperty("address_id") Long addressId, 
      @JsonProperty("person_dto") PersonDto personDto, 
      @JsonProperty("company_dto") CompanyDto companyDto, 
      @JsonProperty("po_box") String poBox, 
      @JsonProperty("street_address") String streetAddress, 
      @JsonProperty("city") String city, 
      @JsonProperty("state") String state, 
      @JsonProperty("zipcode") String zipcode, 
      @JsonProperty("country") String country) {
    this.addressId = addressId;
    this.personDto = personDto;
    this.companyDto = companyDto;
    this.poBox = poBox;
    this.streetAddress = streetAddress;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
    this.country = country;
  }

  public AddressDto() {

  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public PersonDto getPersonDto() {
    return personDto;
  }

  public void setPersonDto(PersonDto personDto) {
    this.personDto = personDto;
  }

  public CompanyDto getCompanyDto() {
    return companyDto;
  }

  public void setCompanyDto(CompanyDto companyDto) {
    this.companyDto = companyDto;
  }

  public String getPoBox() {
    return poBox;
  }

  public void setPoBox(String poBox) {
    this.poBox = poBox;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }
 
  public String getApartmentNumber() {
    return apartmentNumber;
  }

  public void setApartmentNumber(String apartmentNumber) {
    this.apartmentNumber = apartmentNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }
}