Feature: pytweet app

  Scenario: App name in homepage
    Given the app is running
     When I go to the homepage
     Then I see the app name is "PyTweet"

  @wip
  Scenario: Creating a new user
    Given the app is running
     When I create a new account "tweetie" with password "bird"
     Then I should see a message "Successfully created account tweetie"
