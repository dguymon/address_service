package com.dwg.home.controllers.endpoints;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.dwg.home.controllers.AddressController;
import com.dwg.home.domain.Address;
import com.dwg.home.domain.DomainUtils;
import com.dwg.home.dto.AddressDto;
import com.dwg.home.services.AddressService;

import java.net.URI;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Collection of unit tests to test the updateAddress endpoint.
 * 
 * @author Danazn
 *
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class UpdateAddressTest {

  private static final Long SUCCESSFULLY_UPDATED_ADDRESS_ID = 12345L;

  private AddressDto successfulAddressDto;
  private Address successfulAddress;
  private Address successfulUpdatedAddress;

  @MockBean
  private AddressService addressService;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  @Autowired
  protected MockServletContext mockServletContext;

  private MockMvc mockMvc;

  /**
   * Called before each unit test to set up domain objects and DTOs.
   */
  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

    successfulAddressDto = new AddressDto();
    successfulAddressDto.setApartmentNumber("4C");
    successfulAddressDto.setCity("Aldie");
    successfulAddressDto.setCountry("United States");
    successfulAddressDto.setState("VA");
    successfulAddressDto.setStreetAddress("41567 Jolly Way");
    successfulAddressDto.setZipcode("11223");
    successfulAddressDto.setPoBox("P.O. Box 12345");

    successfulAddress = DomainUtils.toDomain(successfulAddressDto);
    successfulUpdatedAddress = successfulAddress;
    successfulUpdatedAddress.setAddressId(SUCCESSFULLY_UPDATED_ADDRESS_ID);
  }

  /**
   * Sets up the AddressController for unit tests.
   * 
   * @author Danazn
   *
   */
  @Configuration
  @EnableWebMvc
  public static class AddressTestConfiguration {

    @Bean
    public AddressController addressController() {
      return new AddressController();
    }
  }

  /**
   * Unit test to verify web application context.
   */
  @Test
  public void verifyWac() {
    ServletContext servletContext = webApplicationContext.getServletContext();
    Assert.assertNotNull(servletContext);
    Assert.assertTrue(servletContext instanceof MockServletContext);
  }

  /**
   * Successful update address unit test.
   */
  @Test
  public void successfulUpdateAddressTest() {

    when(addressService.updateAddress(any(Address.class))).thenReturn(successfulUpdatedAddress);

    String payload = "{ \"apartment_number\" : \"4C\", " 
        + "\"city\" : \"Aldie\", " 
        + "\"address_id\" : \"12345\", " 
        + "\"country\" : \"United States\", " 
        + "\"state\" : \"VA\", " 
        + "\"street_address\" : \"41567 Jolly Way\", " 
        + "\"po_box\" : \"P.O. Box 12345\", " 
        + "\"zipcode\" : \"11223\" }";

    URI url = UriComponentsBuilder.fromUriString("/address/update").build().encode().toUri();

    try {
      mockMvc.perform(put(url)
             .content(payload)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                                                       .andReturn();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Unsuccessful unit test where database fails to update address.
   */
  @Test
  public void unsuccessfulUpdateAddressTestNullUpdatedAddress() {
    when(addressService.updateAddress(any(Address.class))).thenReturn(null);

    String payload = "{ \"apartment_number\" : \"4C\", " 
        + "\"city\" : \"Aldie\", "
        + "\"address_id\" : \"12345\", " 
        + "\"country\" : \"United States\", " 
        + "\"state\" : \"VA\", " 
        + "\"street_address\" : \"41567 Jolly Way\", " 
        + "\"po_box\" : \"P.O. Box 12345\", "
        + "\"zipcode\" : \"11223\" }";

    URI url = UriComponentsBuilder.fromUriString("/address/update")
                                  .build()
                                  .encode().toUri();

    try {
      mockMvc.perform(put(url)
             .content(payload)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotModified())
                                                       .andReturn();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}