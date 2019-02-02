package com.dwg.home.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.Size;

import org.joda.time.DateTime;

@JsonPropertyOrder({"person_id", "first_name", "middle_name",
    "last_name", "birth_date", "occupation"
})
public class PersonDto {

  @JsonProperty("person_id")
  public Long personId;

  @JsonProperty("first_name")
  @Size(max = 50)
  private String firstName;

  @JsonProperty("middle_name")
  @Size(max = 50)
  private String middleName;

  @JsonProperty("last_name")
  @Size(max = 50)
  private String lastName;

  @JsonProperty("birth_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private DateTime birthDate;

  @JsonProperty("occupation")
  @Size(max = 50)
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

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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