package com.dwg.home.domain;

import com.dwg.home.dto.AddressDto;
import com.dwg.home.dto.CompanyDto;
import com.dwg.home.dto.PersonDto;

/**
 * Utility class to convert DTOs to domain objects.
 * 
 * @author Danazn
 *
 */
public class DomainUtils {

  /**
   * Static method for converting AddressDto to Address.
   * 
   * @param addressDto The AddressDto to convert to Address.
   * @return address The Address domain object.
  */
  public static Address toDomain(AddressDto addressDto) {
    if (addressDto == null) {
      return null;
    } else {
      Address address = new Address();
      address.setAddressId(addressDto.getAddressId());
      address.setApartmentNumber(addressDto.getApartmentNumber());
      address.setCity(addressDto.getCity());
      address.setCompany(toDomain(addressDto.getCompanyDto(), 
                                  address.getAddressId()));
      address.setCountry(addressDto.getCountry());
      address.setPerson(toDomain(addressDto.getPersonDto()));
      address.setPoBox(addressDto.getPoBox());
      address.setState(addressDto.getState());
      address.setStreetAddress(addressDto.getStreetAddress());
      address.setZipcode(addressDto.getZipcode());

      return address;
    }
  }

  /**
   * Static method to convert PersonDto to Person.
   * 
   * @param personDto PersonDto to convert
   * @return person Person object
   */
  public static Person toDomain(PersonDto personDto) {
    if (personDto == null) {
      return null;
    } else {
      Person person = new Person();
      person.setBirthDate(personDto.getBirthDate());
      person.setFirstName(personDto.getFirstName());
      person.setLastName(personDto.getLastName());
      person.setMiddleName(personDto.getMiddleName());
      person.setOccupation(personDto.getOccupation());
      person.setPersonId(personDto.getPersonId());

      return person;
    }
  }

  /**
   * Static method for converting CompanyDto to Company.
   * 
   * @param companyDto CompanyDto to convert
   * @param addressId AddressID associated with the Company
   * @return
   */
  public static Company toDomain(CompanyDto companyDto, Long addressId) {
    if (companyDto == null) {
      return null;
    } else {
      Company company = new Company();
      company.setCompanyId(companyDto.getCompanyId());
      company.setCompanyName(companyDto.getCompanyName());

      return company;
    }
  }
}