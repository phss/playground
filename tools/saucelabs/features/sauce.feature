Feature: Connecting to Sauce Labs

  Scenario: Simple assertion
    Given I am in the test page
     When I submit with "Hello from Sauce Labs"
     Then I have a comment with "Hello from Sauce Labs"
