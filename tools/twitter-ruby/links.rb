require "twitter"
require "yaml"

keys = YAML.load_file('config.yaml')

Twitter.configure do |config|
  config.consumer_key = keys["key"]
  config.consumer_secret = keys["secret"]
  config.oauth_token = keys["token"]
  config.oauth_token_secret = keys["token_secret"]
end

Twitter.home_timeline.each do |tweet|
  puts
  puts tweet.from_user
  puts "\"#{tweet.text}\""
  tweet.urls.each do |url|
    puts "- #{url.expanded_url}"
  end
end
