import unittest
from selenium import webdriver


class Selenium2OnSauce(unittest.TestCase):

    def setUp(self):
        desired_capabilities = webdriver.DesiredCapabilities.IPHONE
        desired_capabilities['version'] = '5.0'
        desired_capabilities['platform'] = 'MAC'
        desired_capabilities['name'] = 'Testing Selenium 2 in Python at Sauce'

        self.driver = webdriver.Remote(
            desired_capabilities=desired_capabilities,
            command_executor="http://username-string:access-key-string@ondemand.saucelabs.com:80/wd/hub"
        )
        self.driver.implicitly_wait(30)

    def test_sauce(self):
        self.driver.get('http://saucelabs.com/test/guinea-pig')
        self.assertTrue("I am a page title - Sauce Labs" in self.driver.title)
        comments = self.driver.find_element_by_id('comments')
        comments.send_keys('Hello! I am some example comments.'
                           ' I should be in the page after submitting the form')
        self.driver.find_element_by_id('submit').click()

        commented = self.driver.find_element_by_id('your_comments')
        self.assertTrue('Your comments: Hello! I am some example comments.'
                        ' I should be in the page after submitting the form'
                        in commented.text)
        body = self.driver.find_element_by_xpath('//body')
        self.assertFalse('I am some other page content' in body.text)
        self.driver.find_elements_by_link_text('i am a link')[0].click()
        body = self.driver.find_element_by_xpath('//body')
        self.assertTrue('I am some other page content' in body.text)

    def tearDown(self):
        print("Link to your job: https://saucelabs.com/jobs/%s" % self.driver.session_id)
        self.driver.quit()

if __name__ == '__main__':
    unittest.main()
