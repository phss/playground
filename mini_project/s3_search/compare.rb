require 'yaml'
require 'aws-sdk'

bucket_name_1 = ARGV.shift
bucket_name_2 = ARGV.shift

config = YAML::load(File.open('config'))

AWS.config(access_key_id: config['access'], secret_access_key: config['secret'], region: 'us-east-1')

s3 = AWS::S3.new

def keys_from(s3, bucket_name)
  s3.buckets[bucket_name].objects.map(&:key).map(&:strip).uniq
end

keys_a = keys_from(s3, bucket_name_1)
keys_b = keys_from(s3, bucket_name_2)

puts keys_b
