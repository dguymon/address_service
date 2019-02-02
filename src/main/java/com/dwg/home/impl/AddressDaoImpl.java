package com.dwg.home.impl;

import com.dwg.home.dao.AddressDao;
import com.dwg.home.domain.Address;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressDaoImpl implements AddressDao {

  @Autowired
  EntityManager entityManager;

  @Override
  public Address createAddress(Address address) {
    Session session = entityManager.unwrap(Session.class);
    session.save(address);
    return address;
  }

  @Override
  public Address updateAddress(Address address) {
    Session session = entityManager.unwrap(Session.class);
    session.update(address);
    return address;
  }

  @Override
  public Address deleteAddress(Long addressId) {
    Session session = entityManager.unwrap(Session.class);
    Address address = session.get(Address.class, addressId);
    if (address != null) {
      session.delete(address);
    }

    return address;
  }

  @Override
  public Address readAddress(Long addressId) {
    Session session = entityManager.unwrap(Session.class);
    Address address = session.get(Address.class, addressId);
    if (address != null) {
      return address;
    } else {
      return new Address();
    }
  }
}
