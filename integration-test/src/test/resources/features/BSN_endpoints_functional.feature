Feature: BSN number endpoints cucumber-test feature

  Scenario: Test BSN generation endpoint functionality
    Given the application is up and running
    When endpoint /api/bsn/generate is called
    Then generated BSN is expected to be valid

  Scenario Outline: Test BSN validation endpoint functionality
    Given the application is up and running
    When endpoint /api/bsn/validate/<validNumber> is called
    Then validated BSN is expected to be <valid>

    Examples:
      | validNumber | valid |
      | 033858056   | true  |
      | 166613474   | false |
      | 318777137   | true  |
      | 086063844   | true  |
      | 5567653     | false |