import unittest
from hamcrest import *
from app.api import *
from app.models import *


class ConfigTest(unittest.TestCase):

    def test_app_name_is_set_in_the_db(self):
        assert_that(app_name(), equal_to('PyTweet'))


class UserAccountTest(unittest.TestCase):

    def setUp(self):
        session = Session()
        session.query(User).delete()
        session.commit()

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
