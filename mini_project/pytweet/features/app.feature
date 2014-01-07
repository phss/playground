Feature: pytweet app

  Scenario: App name in homepage
    Given the app is running
     When I go to the homepage
     Then I see the app name is "PyTweet"
