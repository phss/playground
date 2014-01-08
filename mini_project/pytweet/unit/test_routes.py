from flask.ext.testing import TestCase
from app.routes import app
from hamcrest import *
from mock import patch


class RoutesTest(TestCase):

    def create_app(self):
        return app

    @patch("app.api.app_name")
    def test_homepage_rendering(self, mock_api):
        mock_api.return_value = 'Some interesting name'

        response = self.client.get('/')

        self.assert200(response)
        self.assertTemplateUsed('homepage.html')
        self.assertContext('app_name', 'Some interesting name')
