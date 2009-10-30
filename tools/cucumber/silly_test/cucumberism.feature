Feature: Cucumberism
  In order to test cucumber
  As a curious developer
  I want to perform operations on strings and numbers

  Scenario: String contains substring
    Given I have string "blah"
    Then string "la" should be a substring

  Scenario: String contains itself
    Given I have string "blah"
    Then string "blah" should be a substring

  Scenario: Sum two numbers
    Given I have number 13
    And I have another number 42
    When I sum
    Then the result should be 55
    
  Scenario Outline: Summing a lot of combinations
    Given I have number <n1>
    And I have another number <n2>
    When I sum
    Then the result should be <sum>
    Examples:
     | n1 | n2 | sum |
     | 13 | 42 | 55  |
     | 2  | 2  | 4   |
    
  Scenario Outline: Subtracting a lot of combinations
    Given I have number <n1>
    And I have another number <n2>
    When I subtract
    Then the result should be <subtraction>
    Examples:
     | n1 | n2 | subtraction |
     | 42 | 42 | 0           |
     | 42 | 2  | 40          |

