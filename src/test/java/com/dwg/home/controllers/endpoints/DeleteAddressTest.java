package com.dwg.home.controllers.endpoints;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class DeleteAddressTest {

  private static final Long EXISTING_ADDRESS_ID = 12345L;

  private AddressDto successfulAddressDto;
  private Address successfulAddress;
  private Address successfulDeletedAddress;
  
  @MockBean
  private AddressService addressService;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  @Autowired
  protected MockServletContext mockServletContext;

  private MockMvc mockMvc;

  /**
   * Called before each test, sets up domain and DTO objects.
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

    successfulAddress = DomainUtils.toDomain(successfulAddressDto);
    successfulDeletedAddress = successfulAddress;
    successfulDeletedAddress.setAddressId(EXISTING_ADDRESS_ID);
  }

  @Configuration
  @EnableWebMvc
  public static class AddressTestConfiguration {
 
    @Bean
    public AddressController addressController() {
      return new AddressController();
    }
  }

  @Test
  public void verifyWac() {
    ServletContext servletContext = webApplicationContext.getServletContext();
    Assert.assertNotNull(servletContext);
    Assert.assertTrue(servletContext instanceof MockServletContext);
  }

  @Test
  public void successfulDeleteAddressTest() {

    when(addressService.deleteAddress(EXISTING_ADDRESS_ID)).thenReturn(successfulDeletedAddress);

    String payload = "{ \"apartment_number\" : \"4C\", " 
        + "\"address_id\" : \"12345\", " 
        + "\"city\" : \"Aldie\", "
        + "\"country\" : \"United States\", " 
        + "\"state\" : \"VA\", " 
        + "\"street_address\" : \"41567 Jolly Way\", " 
        + "\"zipcode\" : \"11223\" }";

    URI url = UriComponentsBuilder.fromUriString("/address/delete").build().encode().toUri();

    try {
      mockMvc.perform(delete(url)
             .content(payload)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                                                       .andReturn();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Unsuccessful test case to delete address with missing address_id.
   */
  @Test
  public void unsuccessfulDeleteAddressTestNullAddressId() {
    String payload = "{ \"apartment_number\" : \"4C\", " 
        + "\"city\" : \"Aldie\", " 
        + "\"country\" : \"United States\", " 
        + "\"state\" : \"VA\", " 
        + "\"street_address\" : \"41567 Jolly Way\", " 
        + "\"zipcode\" : \"11223\" }";

    URI url = UriComponentsBuilder.fromUriString("/address/delete").build().encode().toUri();

    try {
      mockMvc.perform(delete(url)
             .content(payload)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isUnprocessableEntity())
                                                       .andReturn();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void unsuccessfulDeleteAddressTestNullDeletedAddress() {
    when(addressService.deleteAddress(EXISTING_ADDRESS_ID)).thenReturn(null);

    String payload = "{ \"apartment_number\" : \"4C\", " 
        + "\"address_id\" : \"12345\", " 
        + "\"city\" : \"Aldie\", " 
        + "\"country\" : \"United States\", " 
        + "\"state\" : \"VA\", " 
        + "\"street_address\" : \"41567 Jolly Way\", " 
        + "\"zipcode\" : \"11223\" }";

    URI url = UriComponentsBuilder.fromUriString("/address/delete").build().encode().toUri();

    try {
      mockMvc.perform(delete(url)
             .content(payload)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotModified())
                                                       .andReturn();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}