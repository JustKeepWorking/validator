package com.github.nduyhai.validator;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {

  @NotNull(message = "Name cannot be null")
  private String name;

  @Email(message = "Email should be valid")
  private String email;

  @NotEmpty(message = "Phone must not be empty")
  private String phone;

  @NotEmpty(message = "Full name must not be empty")
  private String fullName;

  @Past(message = "BirthDate must be a past date")
  private LocalDate dateOfBirth;

  @Min(value = 18, message = "Age should not be less than 18")
  @Max(value = 150, message = "Age should not be greater than 150")
  private int age;

  @Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters")
  private String aboutMe;

  @ConsistentDateParameters
  public void reservation(@NotNull @Future LocalDate begin, @NotNull @Future LocalDate end) {
    System.out.format("Reservation from %s to %s", begin.toString(), end.toString());
  }

  @NotNull
  @Size(min = 1)
  public List<@Valid User> relationship() {
    System.out.println("Get relationship");
    return new LinkedList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getAboutMe() {
    return aboutMe;
  }

  public void setAboutMe(String aboutMe) {
    this.aboutMe = aboutMe;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}
