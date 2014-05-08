require 'yaml'
require 'aws-sdk'

bucket_name_1 = ARGV.shift
bucket_name_2 = ARGV.shift

config = YAML::load(File.open('config'))

AWS.config(access_key_id: config['access'], secret_access_key: config['secret'], region: 'us-east-1')

s3 = AWS::S3.new

keys_a = s3.buckets[bucket_name_1].objects.map(&:key).map(&:strip).uniq
keys_b = s3.buckets[bucket_name_2].objects.map(&:key).map(&:strip).uniq

puts keys_b
