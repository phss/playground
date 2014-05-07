require 'yaml'
require 'aws-sdk-core'

config = YAML::load(File.open('config'))

Aws.config = { access_key_id: config['access'], secret_access_key: config['secret'], region: 'us-east-1' }

s3 = Aws.s3

puts s3.list_buckets.buckets.map(&:name)
