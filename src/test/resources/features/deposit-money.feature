Feature: Deposit into account
  As a client
  I want to make a deposit in my account

  Scenario: Deposit money into account
    Given a client with "0.0" EUR in his account
    When he deposits "10.0" EUR into his account
    And he deposits "15.0" EUR into his account
    Then his new balance is "25.0" EUR