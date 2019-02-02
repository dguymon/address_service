package com.dwg.home.services;

import com.dwg.home.domain.Address;
import com.dwg.home.impl.AddressDaoImpl;

import org.springframework.beans.factory.annotation.Autowired;

public class AddressService {

  @Autowired
  AddressDaoImpl addressDaoImpl;

  public Address createAddress(Address address) {
    Address createdAddress = addressDaoImpl.createAddress(address);
    return createdAddress;
  }

  public Address updateAddress(Address address) {
    Address updatedAddress = addressDaoImpl.updateAddress(address);
    return updatedAddress;
  }

  public Address deleteAddress(Long addressId) {
    Address deletedAddress = addressDaoImpl.deleteAddress(addressId);
    return deletedAddress;
  }

  public Address readAddress(Long addressId) {
    Address readAddress = addressDaoImpl.readAddress(addressId);
    return readAddress;
  }
}
