Given /^my sinatra app is running$/ do
end

When /^I go to the root page$/ do
  visit "/"
end

When /^I go to a page with views$/ do
  visit "/with_views"
end

Then /^I should see "([^\"]*)"$/ do |message|
  last_response.body.to_s.chomp.should == message
end

