package com.dwg.home.controllers.endpoints;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.dwg.home.controllers.AddressController;
import com.dwg.home.domain.Address;
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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class) 
@WebAppConfiguration
@ContextConfiguration
public class ReadAddressTest {

  private static final Long EXISTING_ADDRESS_ID = 12345L;
  private static final Long NON_EXISTING_ADDRESS_ID = 6789L;

  private Address existingAddress;

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

    existingAddress = new Address();

    existingAddress.setAddressId(EXISTING_ADDRESS_ID);
    existingAddress.setApartmentNumber("4C");
    existingAddress.setCity("Aldie");
    existingAddress.setCountry("United States");
    existingAddress.setState("VA");
    existingAddress.setStreetAddress("41567 Jolly Way");
    existingAddress.setZipcode("11223");
    existingAddress.setPoBox("P.O. Box 12345");
  }

  /**
   * Statis class to set up AddressController for unit tests.
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
   * Unit test for verifying the web application framework.
   */
  @Test
  public void verifyWac() {
    ServletContext servletContext = webApplicationContext.getServletContext();
    Assert.assertNotNull(servletContext);
    Assert.assertTrue(servletContext instanceof MockServletContext);
  }

  /**
   * Successful unit test for readAddress endpoint.
   */
  @Test
  public void successfulReadAddressTest() {
    when(addressService.readAddress(EXISTING_ADDRESS_ID)).thenReturn(existingAddress);

    UriComponents uriComponents = UriComponentsBuilder.fromUriString("/address/read?addressId={0}")
                                                      .buildAndExpand(EXISTING_ADDRESS_ID);

    URI url = uriComponents.encode().toUri();

    try {
      mockMvc.perform(get(url)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                                                        .andReturn();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Unsuccessful unit test where requested address is not in the database.
   */
  @Test
  public void unsuccessfulReadAddressTestNullReadAddress() {
    when(addressService.readAddress(NON_EXISTING_ADDRESS_ID)).thenReturn(null);

    UriComponents uriComponents = UriComponentsBuilder.fromUriString("/address/read?addressId={0}")
                                                      .buildAndExpand(NON_EXISTING_ADDRESS_ID);

    URI url = uriComponents.encode().toUri();

    try {
      mockMvc.perform(get(url)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNoContent())
                                                       .andReturn();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Unsuccessful unit test when addressId is null.
   */
  @Test
  public void unsuccessfulReadAddressTestNullAddressId() {
    UriComponents uriComponents = UriComponentsBuilder.fromUriString("/address/read?addressId={0}")
                                                      .buildAndExpand("");

    URI url = uriComponents.encode().toUri();

    try {
      mockMvc.perform(get(url)
             .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest())
                                                       .andReturn();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
