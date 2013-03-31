require "twitter"
require "yaml"

keys = YAML.load_file('config.yaml')

Twitter.configure do |config|
  config.consumer_key = keys["key"]
  config.consumer_secret = keys["secret"]
  config.oauth_token = keys["token"]
  config.oauth_token_secret = keys["token_secret"]
end

users_frequency = Twitter.home_timeline(:count => 200).
                          group_by(&:from_user).
                          map { |k, v| [k, v.length] }.
                          sort_by(&:last).
                          reverse.
                          map { |a| a.join(" ") }

puts users_frequency
