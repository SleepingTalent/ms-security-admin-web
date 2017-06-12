@user
Feature: User Features

  Scenario: CreateUser form validates required fields
    Given a user is on the "createUser" page
    When the createUser form details are empty
    And the createUser empty form is submitted
    Then the createUser form validation messages are displayed

  Scenario: CreateUser form resets fields
    Given a user is on the "createUser" page
    When the createUser form details name is set to
      |payNumber|name  |email        |jobTitle|department|location|
      |payNumber|myName|test@test.com|manager |sales     |UK      |
    And the createUser form is reset
    Then the createUser form fields are empty

  Scenario: CreateUser form creates new user
    Given a user is on the "createUser" page
    When the createUser form details name is set to
      |payNumber|name  |email        |jobTitle|department|location|
      |payNumber|myName|test@test.com|manager |sales     |UK      |
    And the createUser form is submitted
    Then the user "myName" is displayed in the users table