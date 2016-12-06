@BACKLOG
Feature: Print account statement
  As a bank client
  I want to see the history (operation, date, amount, balance) of my operations

  Scenario: Print the account history
    Given a client deposits 20.0 EUR in his account on "05/12/2016"
    And  he deposits 10.0 EUR into his account on "06/12/2016"
    And  he withdraws 15.0 EUR form his account on "07/12/2016"
    And  he deposits 10.0 EUR into his account on "08/12/2016"
    When He print his statement he got this output
    """
      | date       | amount | balance |
      | 08/12/2016 | 10     | 20      |
      | 07/12/2016 | -15    | 15      |
      | 06/12/2016 | 10     | 30      |
      | 05/12/2016 | 20     | 20      |
    """
