from flask.ext.testing import TestCase
from app.models import User
from app.routes import app
from mock import patch
from hamcrest import *


class HomepageRouteTest(TestCase):

    def create_app(self):
        return app

    def test_homepage_rendering_when_not_logged_in(self):
        response = self.client.get('/')

        self.assert_200(response)
        self.assert_template_used('homepage.html')
        self.assert_context('user', None)

    def test_homepage_rendering_logged_in_user(self):
        with self.client.session_transaction() as session:
            session['logged_in_user'] = 'loggedin'

        response = self.client.get('/')

        self.assert_200(response)
        self.assert_template_used('homepage.html')
        self.assert_context('user', 'loggedin')


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

    @patch('app.api.authenticate')
    def test_login_existing_user(self, mock_api):
        mock_api.return_value = True
        response = self.client.post('/login', data=dict(
            username='someuser',
            password='somepassword'))

        self.assert_redirects(response, '/')
        with self.client.session_transaction() as session:
            assert_that(session['logged_in_user'], equal_to('someuser'))

    @patch('app.api.authenticate')
    def test_dont_login_if_failed_to_authenticate(self, mock_api):
        mock_api.return_value = False
        response = self.client.post('/login', data=dict(
            username='wrong',
            password='wrong'))

        self.assert_200(response)
        self.assert_template_used('failure.html')
        self.assert_context('message', 'Failed to authenticate')
        with self.client.session_transaction() as session:
            assert_that(session, is_not(has_key('logged_in_user')))

    @patch('app.api.authenticate')
    def test_logout_clear_session(self, mock_api):
        mock_api.return_value = True
        self.client.post('/login', data=dict(
            username='someuser',
            password='somepassword'))
        response = self.client.post('/logout')

        self.assert_redirects(response, '/')
        with self.client.session_transaction() as session:
            assert_that(session, is_not(has_key('logged_in_user')))


class TweetTest(TestCase):

    def create_app(self):
        return app

    def test_create_tweet_page_rendering(self):
        response = self.client.get('/tweet')

        self.assert_200(response)
        self.assert_template_used('tweet.html')

    @patch('app.api.get_account')
    @patch('app.api.create_tweet')
    def test_create_a_tweet(self, mock_tweet_api, mock_user_api):
        submitter = User('loggedin', 'any')
        mock_user_api.return_value = submitter
        with self.client.session_transaction() as session:
            session['logged_in_user'] = 'loggedin'

        response = self.client.post('/tweet', data=dict(
            text='Something interesting'))

        self.assert_redirects(response, '/')
        mock_tweet_api.assert_called_with(submitter, 'Something interesting')

    @patch('app.api.create_tweet')
    def test_dont_create_if_not_logged_in(self, mock_api):
        response = self.client.post('/tweet', data=dict(
            text='Not logged in'))

        self.assert_200(response)
        self.assert_template_used('failure.html')
        self.assert_context('message', 'Login before tweeting')
        assert not mock_api.called
