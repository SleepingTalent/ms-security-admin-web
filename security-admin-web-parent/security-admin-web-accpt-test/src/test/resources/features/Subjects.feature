@subject
Feature: Subjects Features

  Scenario: CreateSubject form validates required fields
    Given a user is on the "createSubject" page
    When the createSubject form details are empty
    And the createSubject empty form is submitted
    Then the createSubject form validation messages are displayed

  Scenario: CreateSubject form resets fields
    Given a user is on the "createSubject" page
    When the createSubject form data is set to
      |username   |password |assignRole|
      |resetTest  |pa55word |false     |
    And the createSubject form is reset
    Then the createSubject form fields are empty


  Scenario: CreateSubject form creates new subject
    Given a user is on the "createSubject" page
    When the createSubject form data is set to
      |username          |password |assignRole|
      |createSubjectTest |pa55word |false     |
    And the createSubject form is submitted
    Then the subject "createSubjectTest" is displayed in the subject table

  Scenario: CreateSubject form creates new subject with roles
    Given a user is on the "createSubject" page
    When the createSubject form data is set to
      |username                  |password |assignRole|
      |createSubjectWithPermTest |pa55word |true      |
    And the createSubject form is submitted
    And the following roles are assigned
      |role                                |
      |documentViewer (Document View Role) |
      |drawingViewer (Drawing View Role)   |
    When the assignRole form is submitted
    And the subject "createSubjectWithPermTest" is displayed in the subject table
    And click on the edit link for the subject "createSubjectWithPermTest"
    And subject is displayed with the assigned roles
      |role                                |
      |documentViewer (Document View Role) |
      |drawingViewer (Drawing View Role)   |

@james
  Scenario: CreateSubject form edits subject roles
    Given a user is on the "createSubject" page
    When the createSubject form data is set to
      |username                  |password |assignRole|
      |editSubjectWithPermTest   |pa55word |true      |
    And the createSubject form is submitted
    And the following roles are assigned
      |role                                |
      |documentViewer (Document View Role) |
    When the assignRole form is submitted
    And the subject "editSubjectWithPermTest" is displayed in the subject table
    And click on the edit link for the subject "editSubjectWithPermTest"
    And subject is displayed with the assigned roles
      |role                                |
      |documentViewer (Document View Role) |
    And the following additional roles are assigned
      |role                                |
      |drawingViewer (Drawing View Role) |
    Then the editSubject form is submitted
    And the subject "editSubjectWithPermTest" is displayed in the subject table
    And click on the edit link for the subject "editSubjectWithPermTest"
    And subject is displayed with the assigned roles
      |role                                |
      |documentViewer (Document View Role) |
      |drawingViewer (Drawing View Role)   |


    Scenario: CreateSubject form edits subject
      Given a user is on the "createSubject" page
      When the createSubject form data is set to
        |username          |password |assignRole|
        |editSubjectTest |pa55word |false     |
      And the createSubject form is submitted
      Then the subject "editSubjectTest" is displayed in the subject table
      And click on the edit link for the subject "editSubjectTest"
      And subject details is edited to
        |username              |
        |editSubjectEditedTest |
      Then the editSubject form is submitted
      And the subject "editSubjectEditedTest" is displayed in the subject table
