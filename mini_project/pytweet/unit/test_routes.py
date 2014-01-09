from flask.ext.testing import TestCase
from app.models import User
from app.routes import app
from mock import patch
from hamcrest import *


class HomepageRouteTest(TestCase):

    def create_app(self):
        return app

    @patch("app.api.app_name")
    def test_homepage_rendering(self, mock_api):
        mock_api.return_value = 'Some interesting name'

        response = self.client.get('/')

        self.assert_200(response)
        self.assert_template_used('homepage.html')
        self.assert_context('app_name', 'Some interesting name')


class CreateAccountRouteTest(TestCase):

    def create_app(self):
        return app

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

    @patch('app.api.create_account')
    def test_render_failure_when_failed_to_create_account(self, mock_api):
        mock_api.side_effect = ValueError('Failed to create account')
        response = self.client.post('/create-account', data=dict(
            username='faileduser',
            password='failedpassword'))

        self.assert_200(response)
        self.assert_template_used('failure.html')
        self.assert_context('message', 'Failed to create account')


class LoginRouteTest(TestCase):

    def create_app(self):
        return app

    def test_login_page_rendering(self):
        response = self.client.get('/login')

        self.assert_200(response)
        self.assert_template_used('login.html')

    @patch('app.api.get_account')
    def test_login_existing_user(self, mock_api):
        mock_api.return_value = User('someuser', 'somepassword')
        response = self.client.post('/login', data=dict(
            username='someuser',
            password='somepassword'))

        self.assert_redirects(response, '/')
        with self.client.session_transaction() as session:
            assert_that(session['logged_in_user'], equal_to('someuser'))