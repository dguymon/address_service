package com.dwg.home.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Person entity class.
 * 
 * @author Danazn
 *
 */
@Entity
@Table(name = "PERSONS")
@EntityListeners(AuditingEntityListener.class)
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "person_id", updatable = false, nullable = false)
  private Long personId;

  @Column(name = "first_name")
  @NotNull
  private String firstName;

  @Column(name = "middle_name")
  private String middleName;

  @Column(name = "last_name")
  @NotNull
  private String lastName;

  @Column(name = "birth_date")
  @NotNull
  private DateTime birthDate;

  @Column(name = "occupation")
  private String occupation;

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public DateTime getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(DateTime birthDate) {
    this.birthDate = birthDate;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }
}