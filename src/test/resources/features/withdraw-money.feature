@BACKLOG
Feature: Retrieve money from account
  As a client
  I want to make a withdrawal from my account

  Scenario: Deposit money into account
    Given an existing client with 20.0 EUR in his account
    When he withdraws 15.0 EUR form his account
    Then his new balance is 5.0 EUR
