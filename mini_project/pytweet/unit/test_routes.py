from flask.ext.testing import TestCase
from app.routes import app
from hamcrest import *


class RoutesTest(TestCase):

    def create_app(self):
        return app

    def test_homepage_content(self):
        response = self.client.get('/')
        assert_that(response.data, contains_string('PyTweet'))
