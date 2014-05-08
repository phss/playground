require 'yaml'
require 'aws-sdk'

bucket_name = ARGV.shift
key = ARGV.shift

config = YAML::load(File.open('config'))

AWS.config(access_key_id: config['access'], secret_access_key: config['secret'], region: 'us-east-1')

s3 = AWS::S3.new

obj = s3.buckets[bucket_name].objects[key]

puts obj.read
