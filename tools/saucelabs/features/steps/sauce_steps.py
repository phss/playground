from behave import given, when, then, step

@given(u'I am in the test page')
def step_impl(context):
  context.driver.get('http://saucelabs.com/test/guinea-pig')
  assert "I am a page title - Sauce Labs" in context.driver.title

@when(u'I submit with "{text}"')
def step_impl(context, text):
  comments = context.driver.find_element_by_id('comments')
  comments.send_keys(text)
  context.driver.find_element_by_id('submit').click()

@then(u'I have a comment with "{text}"')
def step_impl(context, text):
  commented = context.driver.find_element_by_id('your_comments')
  assert text in commented.text
