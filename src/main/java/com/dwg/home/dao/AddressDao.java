package com.dwg.home.dao;

import com.dwg.home.domain.Address;

/**
 * Interface DAO layer for address-related CRUD operations.
 * 
 * @author Danazn
 *
 */
public interface AddressDao {
  Address createAddress(Address address);
  
  Address updateAddress(Address address);
  
  Address deleteAddress(Long addressId);
  
  Address readAddress(Long addressId);
}
