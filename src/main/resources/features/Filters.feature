Feature: Testing the filters page

  Rule: User is able to edit or add filter
    Background:
      Given Anonymous user opens Login page
      And Anonymous user logins with 'superadmin' login and 'erebus' password

    @ui @filters
    Scenario: User is able to edit filter name on the Filters page
      When Registered user opens Filters page
      Then Registered user edits the filter name to a new one:
        | dev2   |
        | qa4    |
        | stage2 |
        | qa6    |
        | stage  |

    @ui @filters
    Scenario Outline: User is able to add a new filter
      Given Registered user opens Filters page
      When Registered user clicks on 'Add new filter' button
      And Registered user adds new filter with:
        | filterValue   | filterName   |
        | <filterValue> | <filterName> |
      Then Registered user sees new filter
      And Registered user deletes filter
      Examples:
        | filterValue | filterName            |
        | stg1        | smoke_regression_stg1 |
        | smoke_qa5   | SMOKE ON QA5          |
        | qa7         | QA7_launches          |
        | DEV1        | dev1_env              |
        | qa1         | qa1 env               |
