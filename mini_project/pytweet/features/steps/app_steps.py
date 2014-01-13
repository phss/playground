from app import routes, api, models
from hamcrest import *
from behave import given, when, then
import flask

@given('the app is running')
def step(context):
    context.client = routes.app.test_client()

@given(u"there's an account \"{name}\" with password \"{password}\"")
def impl(context, name, password):
    api.create_account(name, password)

@given(u'I am logged as "{name}"')
def impl(context, name):
    password = "blahblahblah"
    create_account(context, name, password)
    login(context, name, password)

@when(u'I go to the homepage')
def step(context):
    context.response = context.client.get('/')

@when(u'I create a new account "{name}" with password "{password}"')
def create_account(context, name, password):
    context.response = context.client.post('/create-account', data=dict(
        username=name,
        password=password))

@when(u'I login as "{name}" with password "{password}"')
def login(context, name, password):
    context.response = context.client.post('/login', data=dict(
        username=name,
        password=password))

@when(u'I logout')
def impl(context):
    context.response = context.client.post('/logout')

@when(u'I submit a tweet "{tweet}"')
def impl(context, tweet):
    context.tweet = tweet
    context.response = context.client.post('/tweet', data=dict(text=tweet))

@then(u'I see the app name is "{app_name}"')
def step(context, app_name):
    assert_that(context.response.data, contains_string(app_name))

@then(u'I should see a message "{message}"')
def step(context, message):
    assert_that(context.response.data, contains_string(message))

@then(u'I should be logged in as "{name}"')
def impl(context, name):
    with context.client.session_transaction() as session:
        assert_that(session['logged_in_user'], equal_to(name))

@then(u'no one should be logged in')
def impl(context):
    with context.client.session_transaction() as session:
            assert_that(session, is_not(has_key('logged_in_user')))

@then(u'tweet should exist')
def impl(context):
    assert False