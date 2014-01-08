import unittest
from hamcrest import *
from app.api import *


class ApiTest(unittest.TestCase):

    def test_app_name_is_set_in_the_db(self):
        assert_that(app_name(), equal_to('PyTweet'))
