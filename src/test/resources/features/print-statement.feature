Feature: Print account statement
  As a bank client
  I want to see the history (operation. date. amount. balance) of my operations


  Scenario: Print the account history
    Given a client deposits "20.0" EUR in his account on "2016-12-05"
    And  he deposits "10.0" EUR into his account on "2016-12-06"
    And  he withdraws "15.0" EUR form his account on "2016-12-07"
    And  he deposits "10.0" EUR into his account on "2016-12-08"
    When He print his statement he got this output
    """
    | date       | amount | balance |
    | 2016-12-08 | 10.00 | 25.00   |
    | 2016-12-07 | -15.00 | 15.00   |
    | 2016-12-06 | 10.00 | 30.00   |
    | 2016-12-05 | 20.00 | 20.00   |

    """
