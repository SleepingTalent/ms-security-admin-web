@permission
Feature: Permission Features

  Scenario: CreatePermission form validates required fields
    Given a user is on the "createPermission" page
    When the createPermission form details are empty
    And the createPermission empty form is submitted
    Then the createPermission form validation messages are displayed

  Scenario: CreatePermission form resets fields
    Given a user is on the "createPermission" page
    When the createPermission form data is set to
    |name      |description   |
    |resetTest |resetTestDesc |
    And the createPermission form is reset
    Then the createPermission form fields are empty

  Scenario: CreatePermission form creates new permission
    Given a user is on the "createPermission" page
    When the createPermission form data is set to
      |name           |description            |
      |createPermTest |createPermTestDesc     |
    And the createPermission form is submitted
    Then the permission "createPermTest" is displayed in the permissions table