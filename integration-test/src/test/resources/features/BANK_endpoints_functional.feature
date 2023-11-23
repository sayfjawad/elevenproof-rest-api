Feature: BankNumber endpoints cucumber-test feature

  Scenario: Test Bank generation endpoint functionality
    Given the application is up and running
    When endpoint /api/bank/generate is called
    Then generated BANK is expected to be valid

  Scenario Outline: Test Bank validation endpoint functionality
    Given the application is up and running
    When endpoint /api/bank/validate/<validNumber> is called
    Then validated BANK is expected to be <valid>

    Examples:
      | validNumber | valid |
      | 1621868257  | true  |
      | 0243886143  | false |
      | 0420301143  | true  |
      | 7153405860  | true  |
      | 5567653111  | false |