import unittest
from hamcrest import *
from app.api import *


class ConfigTest(unittest.TestCase):

    def test_app_name_is_set_in_the_db(self):
        assert_that(app_name(), equal_to('PyTweet'))


class UserAccountTest(unittest.TestCase):

    def setUp(self):
        with WriteSession() as session:
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
            session.query(User).delete()

    def test_authenticate_existing_user(self):
        create_account('somename', 'somepass')

        assert authenticate('somename', 'somepass')