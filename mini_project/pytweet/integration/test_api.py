import unittest
from hamcrest import *
from app.api import *


class UserAccountTest(unittest.TestCase):

    def setUp(self):
        with WriteSession() as session:
            session.query(Tweet).delete()
            session.query(User).delete()

    def test_returns_none_when_getting_inexistant_account(self):
        assert_that(get_account('noone'), is_(None))

    def test_create_an_account(self):
        create_account('somename', 'somepass')

        user = get_account('somename')

        assert_that(user.username, equal_to('somename'))
        assert_that(user.password, equal_to('somepass'))

    def test_fails_to_create_account_if_username_or_password_are_blank(self):
        self.assertRaises(ValueError, create_account, '', 'somepass')
        self.assertRaises(ValueError, create_account, 'someuser', '')

    def test_fails_to_create_account_if_it_already_exists(self):
        create_account('somename', 'somepass')

        self.assertRaises(ValueError, create_account, 'somename', 'somepass')


class AuthenticationTest(unittest.TestCase):

    def setUp(self):
        with WriteSession() as session:
            session.query(Tweet).delete()
            session.query(User).delete()

    def test_authenticate_existing_user(self):
        create_account('somename', 'somepass')

        assert authenticate('somename', 'somepass')

    def test_fail_authentication_of_inexistant_user(self):
        assert not authenticate('dont', 'exist')

    def test_fail_authentication_is_password_is_wrong(self):
        create_account('somename', 'somepass')

        assert not authenticate('somename', 'anotherpass')


class TweetingTest(unittest.TestCase):

    def setUp(self):
        with WriteSession() as session:
            session.query(Tweet).delete()
            session.query(User).delete()
        create_account('somename', 'somepass')
        self.user = get_account('somename')

    def test_create_a_new_tweet(self):
        create_tweet(self.user, 'Creating a tweet')

        assert_that(self.user.tweets, has_length(1))
        assert_that(self.user.tweets[0].text, equal_to('Creating a tweet'))


    def test_fetching_tweets(self):
        create_tweet(self.user, 'A, B, C')
        create_tweet(self.user, '1, 2, 3')
        create_tweet(self.user, 'You and me')

        tweet_texts = [tweet.text for tweet in get_tweets(self.user)]

        assert_that(tweet_texts, contains('A, B, C', '1, 2, 3', 'You and me'))
