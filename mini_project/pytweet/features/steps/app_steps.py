from app import routes
from hamcrest import *

@given('the app is running')
def step(context):
    context.client = routes.app.test_client()

@when(u'I go to the homepage')
def step(context):
    context.response = context.client.get('/')

@when(u'I create a new account "{name}" with password "{password}"')
def impl(context, name, password):
    assert False

@then(u'I see the app name is "{app_name}"')
def step(context, app_name):
    assert_that(context.response.data, contains_string(app_name))

@then(u'Then I should see a message "{message}"')
def step(context, message):
    assert False