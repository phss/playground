Given /^I have string "([^\"]*)"$/ do |string|
  @string = string
end

Then /^string "([^\"]*)" should be a substring$/ do |substring|
  @string.include?(substring).should be_true # FIXME: argh! must find matcher for include?
end

Given /^I have number (\d+)$/ do |number|
  @number = number.to_i
end

Given /^I have another number (\d+)$/ do |another|
  @another = another.to_i 
end

When /^I sum$/ do
  @sum = @number + @another
end

When /^I subtract$/ do
  @sum = @number - @another
end

Then /^the result should be (\d+)$/ do |result|
  @sum.should == result.to_i
end

