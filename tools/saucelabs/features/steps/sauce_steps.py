from behave import given, when, then, step

@given(u'I am in the test page')
def step_impl(context):
  context.driver.get('http://saucelabs.com/test/guinea-pig')
  assert "I am a page title - Sauce Labs" in context.driver.title

@when(u'I submit with "Hello from Sauce Labs"')
def step_impl(context):
  assert False
      
@then(u'I have a comment with "Hello from Sauce Labs"')
def step_impl(context):
  assert False
