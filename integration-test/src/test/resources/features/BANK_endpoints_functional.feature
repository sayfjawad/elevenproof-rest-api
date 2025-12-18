Feature: BankNumber endpoints cucumber-test feature

  Scenario: Test Bank generation endpoint functionality
    Given the application is up and running
    When endpoint /api/bank/generate is called
    Then generated BANK is expected to be valid