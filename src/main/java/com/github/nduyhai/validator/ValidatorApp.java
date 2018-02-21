package com.github.nduyhai.validator;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

/**
 * See more http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/
 */
public class ValidatorApp {

  public static void main(String[] args) throws NoSuchMethodException {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();

    System.out.println("--Validate Object");
    final User user = new User();
    {
      user.setName("evil");
      user.setFullName("The Evil");
      user.setAboutMe("Its all about me!");
      user.setAge(50);
      user.setEmail("Every@Where");
      user.setDateOfBirth(LocalDate.of(2020, 12, 1));
    }
    Set<ConstraintViolation<User>> violations = validator.validate(user);
    for (ConstraintViolation<User> violation : violations) {
      System.err.println(violation.getMessage());
    }

    System.out.println("--Validate Method parameters");
    final ExecutableValidator executableValidator = factory.getValidator().forExecutables();
    Method method = User.class.getMethod("reservation", LocalDate.class, LocalDate.class);
    Object[] parameters = {LocalDate.of(2020, 10, 1), LocalDate.of(2020, 10, 1)};
    violations = executableValidator.validateParameters(user, method, parameters);
    for (ConstraintViolation<User> violation : violations) {
      System.err.println(violation.getMessage());
    }

    System.out.println("--Validate Method return values");
    method = User.class.getMethod("relationship");
    final List<User> users = new LinkedList<>();
    users.add(user);
    violations = executableValidator.validateReturnValue(user, method, users);
    for (ConstraintViolation<User> violation : violations) {
      System.err.println(violation.getMessage());
    }


  }


}

