package com.dwg.home.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.dwg.home.domain.Address;
import com.dwg.home.domain.DomainUtils;
import com.dwg.home.dto.AddressDto;
import com.dwg.home.dto.DtoUtils;
import com.dwg.home.services.AddressService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AddressController with RESTful endpoints for address-related CRUD operations.
 * 
 * @author Danazn
 *
 */
@RestController
public class AddressController {

  @Autowired
  AddressService addressService;

  /**
   * RESTful endpoint to create an Address.
   * 
   * @param incomingDto The incoming AddressDto to create.
   * @return HttpEntity An HttpEntity containing the created AddressDto, if successful.
   */
  @PostMapping(value = "/address/create", 
               consumes = MediaType.APPLICATION_JSON_VALUE, 
               produces = MediaType.APPLICATION_JSON_VALUE)
  public HttpEntity<AddressDto> createAddress(@Valid @RequestBody AddressDto incomingDto) {

    Address address = DomainUtils.toDomain(incomingDto);
    Address createdAddress = addressService.createAddress(address); 
    AddressDto createdAddressDto = null;

    if (createdAddress != null) {
      createdAddressDto = DtoUtils.toDto(createdAddress);
      createdAddressDto.add(linkTo(methodOn(AddressController.class)
                       .createAddress(incomingDto))
                       .withSelfRel());
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(createdAddressDto, HttpStatus.OK);
  }

  /**
   * RESTful endpoint to update an existing Address record.
   * 
   * @param incomingDto The AddressDto with the changes to update.
   * @return HttpEntity An HttpEntity with the updated AddressDto, if successful.
   */
  @PutMapping(value = "/address/update", 
              consumes = MediaType.APPLICATION_JSON_VALUE, 
              produces = MediaType.APPLICATION_JSON_VALUE)
  public HttpEntity<AddressDto> updateAddress(@Valid @RequestBody AddressDto incomingDto) {
    Address address = DomainUtils.toDomain(incomingDto);
    Address updatedAddress = addressService.updateAddress(address);
    AddressDto updatedAddressDto = null;

    if (updatedAddress != null) {
      updatedAddressDto = DtoUtils.toDto(updatedAddress);
      updatedAddressDto.add(linkTo(methodOn(AddressController.class)
                       .updateAddress(incomingDto))
                       .withSelfRel());
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    return new ResponseEntity<>(updatedAddressDto, HttpStatus.OK);
  }

  /**
   * RESTful endpoint to delete an existing address record.
   * 
   * @param incomingDto The AddressDto to delete from the database.
   * @return HttpEntity An HttpEntity with the AddressDto that was deleted, if successful.
   */
  @DeleteMapping(value = "/address/delete", 
                 produces = MediaType.APPLICATION_JSON_VALUE)
  public HttpEntity<AddressDto> deleteAddress(@RequestBody AddressDto incomingDto) {

    Address deletedAddress = null;
    AddressDto deletedAddressDto = null;
    if (incomingDto.getAddressId() != null) {
      deletedAddress = addressService.deleteAddress(incomingDto.getAddressId());
    } else {
      return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    if (deletedAddress != null) {
      deletedAddressDto = DtoUtils.toDto(deletedAddress);
      deletedAddressDto.add(linkTo(methodOn(AddressController.class)
                       .deleteAddress(incomingDto))
                       .withSelfRel());
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    return new ResponseEntity<>(deletedAddressDto, HttpStatus.OK);
  }

  /**
   * RESTful endpoint to retrieve an existing Address record.
   * 
   * @param addressId The id of the Address record to retrieve.
   * @return HttpEntity An HttpEntity with the retrieved AddressDto, if it exists in the database.
   */
  @GetMapping(value = "/address/read", 
              produces = MediaType.APPLICATION_JSON_VALUE)
  public HttpEntity<AddressDto> readAddress(@RequestParam(value = "addressId", 
                                                          required = true) Long addressId) {
    Address readAddress = null;
    AddressDto readAddressDto = null;
    if (addressId != null) {
      readAddress = addressService.readAddress(addressId);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    if (readAddress != null) {
      readAddressDto = DtoUtils.toDto(readAddress);
      readAddressDto.add(linkTo(methodOn(AddressController.class)
                    .readAddress(addressId))
                    .withSelfRel());
    } else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(readAddressDto, HttpStatus.OK);
  }
}