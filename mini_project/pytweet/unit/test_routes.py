from flask.ext.testing import TestCase
from app.routes import app
from mock import patch


class RoutesTest(TestCase):

    def create_app(self):
        return app

    @patch("app.api.app_name")
    def test_homepage_rendering(self, mock_api):
        mock_api.return_value = 'Some interesting name'

        response = self.client.get('/')

        self.assert_200(response)
        self.assert_template_used('homepage.html')
        self.assert_context('app_name', 'Some interesting name')

    def test_create_account_rendering(self):
        response = self.client.get('/create-account')

        self.assert_200(response)
        self.assert_template_used('create_account.html')

    @patch('app.api.create_account')
    def test_creating_an_user(self, mock_api):
        response = self.client.post('/create-account', data=dict(
            username='someuser',
            password='somepassword'))

        self.assert_200(response)
        self.assert_template_used('account_created.html')
        mock_api.assert_called_with('someuser', 'somepassword')