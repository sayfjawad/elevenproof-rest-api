Feature: An example

  Scenario Outline: Test that all endpoints are available
    Given the application is up and running
    When endpoint <endpoint> is called
    Then status of response is expected to be equal to 200

    Examples:
      | description                    | endpoint                      |
      | health endpoint                | /appinfo/health/liveness      |
      | generate bsn endpoint          | /api/bsn/generate             |
      | validate bsn endpoint          | /api/bsn/validate/123456789   |
      | generate bank account endpoint | /api/bank/generate            |
      | validate bank account endpoint | /api/bank/validate/1234567890 |
