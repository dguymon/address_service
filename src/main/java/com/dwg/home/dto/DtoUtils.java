package com.dwg.home.dto;

import com.dwg.home.domain.Address;
import com.dwg.home.domain.Company;
import com.dwg.home.domain.Person;

import com.dwg.home.dto.AddressDto;
import com.dwg.home.dto.CompanyDto;
import com.dwg.home.dto.PersonDto;

/**
 * Static util methods to convert domain objects.
 * to their DTO equivalent
 * 
 * @author Danazn
 *
 */
public class DtoUtils {

  /**
   * Converts an Address domain object to it's DTO equivalent.
   * 
   * @param address The Address domain object
   * @return addressDto The AddressDto
   */
  public static AddressDto toDto(Address address) { 
    if (address != null) {

      Person person = address.getPerson();
      PersonDto personDto = new PersonDto();
      if (person != null) {
        personDto = toDto(person);
      }

      Company company = address.getCompany();
      CompanyDto companyDto = new CompanyDto();
      if (company != null) {
        companyDto = toDto(company);
      }

      AddressDto addressDto = new AddressDto(
          address.getAddressId(),
          personDto,
          companyDto,
          address.getPoBox(), 
          address.getStreetAddress(),
          address.getCity(),
          address.getState(),
          address.getZipcode(),
          address.getCountry());

      return addressDto;

    } else {
      return null;
    }
  }

  /**
   * Converts a Person domain object to its PersonDto equivalent.
   * 
   * @param person The Person domain object
   * @return personDto The PersonDto
   */
  public static PersonDto toDto(Person person) {
    if (person != null) {
      PersonDto personDto = new PersonDto();
      personDto.setPersonId(person.getPersonId());
      personDto.setBirthDate(person.getBirthDate());
      personDto.setFirstName(person.getFirstName());
      personDto.setLastName(person.getLastName());
      personDto.setMiddleName(person.getMiddleName());
      personDto.setOccupation(person.getOccupation());

      return personDto;
    } else {
      return null;
    }
  }

  /**
   * Converts a Company domain object to its CompanyDto equivalent.
   * 
   * @param company The Company domain object
   * @return companyDto The CompanyDto
   */
  public static CompanyDto toDto(Company company) {
    if (company != null) {
      CompanyDto companyDto = new CompanyDto();
      companyDto.setCompanyId(company.getCompanyId());
      companyDto.setCompanyName(company.getCompanyName());

      return companyDto;
    } else {
      return null;
    }
  }
}
