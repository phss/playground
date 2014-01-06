from selenium import webdriver

def before_all(context):
  sauce_executor = open('.config').read().rstrip()

  desired_capabilities = webdriver.DesiredCapabilities.IPHONE
  desired_capabilities['version'] = '5.0'
  desired_capabilities['platform'] = 'MAC'
  desired_capabilities['name'] = 'Testing iOS with Sauce Labs'

  context.driver = webdriver.Remote(
      desired_capabilities=desired_capabilities,
      command_executor=sauce_executor)
  context.driver.implicitly_wait(30)

def after_all(context):
  context.driver.quit()
