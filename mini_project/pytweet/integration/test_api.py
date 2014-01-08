import unittest
from hamcrest import *
from app.api import *
from app.models import *


class ApiTest(unittest.TestCase):

    def test_app_name_is_set_in_the_db(self):
        assert_that(app_name(), equal_to('PyTweet'))

    def test_create_an_account(self):
        create_account('somename', 'somepass')

        user = Session().query(User).filter_by(username='somename').first()

        assert_that(user.username, equal_to('somename'))
        assert_that(user.password, equal_to('somepass'))

    def test_fails_to_create_account_if_username_or_password_are_blank(self):
        self.assertRaises(AssertionError, create_account, '', 'somepass')
        self.assertRaises(AssertionError, create_account, 'someuser', '')
