@navigation
@wip
Feature: Navigation Features

  @navigationPanel
  Scenario Outline: <linkName> Link Navigates To <endPage> When User is on the <startPage> Page
    Given a user is on the "<startPage>" page
    When the "<linkName>" navigation link is clicked
    Then the "<endPage>" page is displayed

    Examples:
    | startPage   | linkName    | endPage     |
    | dashboard   | dashboard   | dashboard   |
    | dashboard   | users       | users       |
    | dashboard   | subjects    | subjects    |
    | dashboard   | roles       | roles       |
    | dashboard   | permissions | permissions |
    | users       | dashboard   | dashboard   |
    | users       | users       | users       |
    | users       | subjects    | subjects    |
    | users       | roles       | roles       |
    | users       | permissions | permissions |
    | subjects    | dashboard   | dashboard   |
    | subjects    | users       | users       |
    | subjects    | subjects    | subjects    |
    | subjects    | roles       | roles       |
    | subjects    | permissions | permissions |
    | roles       | dashboard   | dashboard   |
    | roles       | users       | users       |
    | roles       | subjects    | subjects    |
    | roles       | roles       | roles       |
    | roles       | permissions | permissions |
    | permissions | dashboard   | dashboard   |
    | permissions | users       | users       |
    | permissions | subjects    | subjects    |
    | permissions | roles       | roles       |
    | permissions | permissions | permissions |

  @dashboard
  Scenario Outline: <linkName> Link Navigates To <endPage> When User is on the <startPage> Page
    Given a user is on the "<startPage>" page
    When the "<linkName>" dashboard link is clicked
    Then the "<endPage>" page is displayed

    Examples:
      | startPage   | linkName              | endPage           |
      | dashboard   | usersLink             | users             |
      | dashboard   | createUserLink        | createUser        |
      | dashboard   | subjectsLink          | subjects          |
      | dashboard   | createSubjectLink     | createSubject     |
      | dashboard   | rolesLink             | roles             |
      | dashboard   | createRoleLink        | createRole        |
      | dashboard   | permissionsLink       | permissions       |
      | dashboard   | createPermissionLink  | createPermission  |