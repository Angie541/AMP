Feature: Testing the login page

  @ui @login
  Scenario Outline: User logins with different credentials to the Report Portal
    Given Anonymous user opens Login page
    When Anonymous user logins with '<login>' login and '<password>' password
    Then Registered user is redirected to Dashboard page
    Examples:
      | login       | password |
      | superadmin  | erebus   |
      | default     | 1q2w3e   |
      | auto_tester | 12345AT  |
      | developer   | 1234567  |
      | analytic    | 00000000 |
