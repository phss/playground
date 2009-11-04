Feature: Sinatra
  In order to test sinatra with webrat
  As a developer
  I want to see if this works

  Scenario: Hello world
    Given my sinatra app is running
    When I go to the root page
    Then I should see "Hello World"

  Scenario: Hello world with views
    Given my sinatra app is running
    When I go to a page with views
    Then I should see "Hello World...with views!"
