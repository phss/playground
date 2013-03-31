require "twitter"
require "yaml"

keys = YAML.load_file('config.yaml')

Twitter.configure do |config|
  config.consumer_key = keys["key"]
  config.consumer_secret = keys["secret"]
  config.oauth_token = keys["token"]
  config.oauth_token_secret = keys["token_secret"]
end

last_tweet = Twitter.home_timeline(:max_id => 314610494713511937, 
                                   :count => 200).last

puts last_tweet.id
puts last_tweet.from_user
puts last_tweet.created_at
puts last_tweet.full_text
