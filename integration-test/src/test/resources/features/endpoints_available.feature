Feature: An example

  Scenario Outline: Test that all endpoints are available
    Given the application is up and running
    When endpoint <endpoint> is called status <status> is expected
    Then all endpoints are available

    Examples:
      | description                    | endpoint                      | status |
      | health endpoint                | /appinfo/health/liveness      | 200    |
      | generate bsn endpoint          | /api/bsn/generate             | 200    |
      | validate bsn endpoint          | /api/bsn/validate/123456789   | 200    |
      | generate bank account endpoint | /api/bank/generate            | 200    |
      | validate bank account endpoint | /api/bank/validate/1234567890 | 200    |
