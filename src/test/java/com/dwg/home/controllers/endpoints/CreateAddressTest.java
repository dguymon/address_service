package com.dwg.home.controllers.endpoints;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class CreateAddressTest {

  private static final Long SUCCESSFULLY_CREATED_ADDRESS_ID = 12345L;

  private AddressDto successfulAddressDto;
  private Address successfulAddress;
  private Address successfulCreatedAddress;

  @MockBean
  private AddressService addressService;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  @Autowired
  protected MockServletContext mockServletContext;

  private MockMvc mockMvc;

  /**
   * Called before each unit test, sets up the required business and DTO objects.
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
    successfulCreatedAddress = successfulAddress;
    successfulCreatedAddress.setAddressId(SUCCESSFULLY_CREATED_ADDRESS_ID);
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
  public void successfulCreateAddressTest() {

    when(addressService.createAddress(any(Address.class))).thenReturn(successfulCreatedAddress);

    String payload = "{ \"apartment_number\" : \"4C\", "
        + "\"city\" : \"Aldie\", "
        + "\"country\" : \"United States\", " 
        + "\"state\" : \"VA\", "
        + "\"street_address\" : \"41567 Jolly Way\", " 
        + "\"zipcode\" : \"11223\" }";

    URI url = UriComponentsBuilder.fromUriString("/address/create").build().encode().toUri();

    try {
      mockMvc.perform(post(url)
             .content(payload)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                                                       .andReturn();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void unsuccessfulCreateAddressTestNullCreatedAddress() {
    when(addressService.createAddress(any(Address.class))).thenReturn(null);

    String payload = "{ \"apartment_number\" : \"4C\", " 
        + "\"city\" : \"Aldie\", " 
        + "\"country\" : \"United States\", " 
        + "\"state\" : \"VA\", " 
        + "\"street_address\" : \"41567 Jolly Way\", " 
        + "\"zipcode\" : \"11223\" }";

    URI url = UriComponentsBuilder.fromUriString("/address/create").build().encode().toUri();

    try {
      mockMvc.perform(post(url)
             .content(payload)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isInternalServerError())
                                                       .andReturn();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests an unsuccessful test case where the po_box is too long.
   */
  @Test
  public void unsuccessfulCreateAddressTestLongPoBox() {

    String payload = "{ \"apartment_number\" : \"4C\", " 
        + "\"city\" : \"Aldie\", " 
        + "\"country\" : \"United States\", " 
        + "\"state\" : \"VA\", " 
        + "\"street_address\" : \"41567 Jolly Way\", "
        + "\"po_box\" : \"123456789123456789123456789"
        + "123456789123456789123456789123456789123456789\", " 
        + "\"zipcode\" : \"11223\" }";

    URI url = UriComponentsBuilder.fromUriString("/address/create").build().encode().toUri();

    try {
      mockMvc.perform(post(url)
             .content(payload)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest())
                                                       .andReturn();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void unsuccessfulCreateAddressTestLongStreetAddress() {

    String payload = "{ \"apartment_number\" : \"4C\", " 
        + "\"city\" : \"Aldie\", " 
        + "\"country\" : \"United States\", " 
        + "\"state\" : \"VA\", " 
        + "\"street_address\" : \"41567 JollyHollyJollyHollyJollyHolly"
        +  "JollyHollyJollyHollyJollyHollyJollyHollyJollyHollyJollyHol"
        +  "lyJollyHollyJollyHollyJollyHollyJollyHollyJollyHollyJollyH"
        + "ollyJollyHollyJollyHollyJollyHollyJollyHollyJollyHollyJolly"
        + "HollyJollyHollyJollyHolly Way\", " 
        + "\"zipcode\" : \"11223\" }";

    URI url = UriComponentsBuilder.fromUriString("/address/create").build().encode().toUri();

    try {
      mockMvc.perform(post(url)
             .content(payload)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest())
                                                       .andReturn();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}