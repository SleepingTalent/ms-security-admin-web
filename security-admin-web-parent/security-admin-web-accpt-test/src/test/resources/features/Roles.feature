@role
Feature: Role Features

  Scenario: CreateRole form validates required fields
    Given a user is on the "createRole" page
    When the createRole form details are empty
    And the createRole empty form is submitted
    Then the createRole form validation messages are displayed

  Scenario: CreateRole form resets fields
    Given a user is on the "createRole" page
    When the createRole form data is set to
      |name      |description   |assignPermission|
      |resetTest |resetTestDesc |false           |
    And the createRole form is reset
    Then the createRole form fields are empty

  Scenario: CreateRole form creates new role
    Given a user is on the "createRole" page
    When the createRole form data is set to
      |name           |description        |assignPermission|
      |createRoleTest |createRoleTestDesc |false           |
    And the createRole form is submitted
    Then the role "createRoleTest" is displayed in the role table

  Scenario: CreateRole form creates new role with permissions
    Given a user is on the "createRole" page
    When the createRole form data is set to
      |name                   |description                |assignPermission|
      |createRoleWithPermTest |createRoleWithPermTestDesc |true            |
    And the createRole form is submitted
    And the following permissions are assigned
      |permission                                |
      |drawing:viewer (Drawing View Permission)  |
      |document:viewer (Document View Permission)|
    When the assignPermission form is submitted
    And the role "createRoleWithPermTest" is displayed in the role table
    And click on the edit link for the role "createRoleWithPermTest"
    And role is displayed with the assigned permissions
      |permission                                |
      |drawing:viewer (Drawing View Permission)  |
      |document:viewer (Document View Permission)|

  Scenario: CreateRole form edits role permissions
    Given a user is on the "createRole" page
    When the createRole form data is set to
      |name                 |description              |assignPermission|
      |editRoleWithPermTest |editRoleWithPermTestDesc |true            |
    And the createRole form is submitted
    And the following permissions are assigned
      |permission                                |
      |document:viewer (Document View Permission)|
    When the assignPermission form is submitted
    And the role "editRoleWithPermTest" is displayed in the role table
    And click on the edit link for the role "editRoleWithPermTest"
    And role is displayed with the assigned permissions
      |permission                                |
      |document:viewer (Document View Permission)|
    And the following additional permissions are assigned
      |permission                                |
      |drawing:viewer (Drawing View Permission) |
    Then the editRole form is submitted
    And the role "editRoleWithPermTest" is displayed in the role table
    And click on the edit link for the role "editRoleWithPermTest"
    And role is displayed with the assigned permissions
      |permission                                |
      |document:viewer (Document View Permission)|
      |drawing:viewer (Drawing View Permission)  |

  Scenario: CreateRole form edits role
    Given a user is on the "createRole" page
    When the createRole form data is set to
      |name         |description       |assignPermission|
      |editRoleTest |editRoleTestDesc |false           |
    And the createRole form is submitted
    And the role "editRoleTest" is displayed in the role table
    And click on the edit link for the role "editRoleTest"
    And role details is edited to
    |name               |description       |
    |editRoleEditedTest |editRoleEditedTestDesc |
    Then the editRole form is submitted
    And the role "editRoleEditedTest" is displayed in the role table