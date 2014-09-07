require 'yaml'
require 'aws-sdk'
require 'rexml/document'
include REXML

bucket_name = ARGV.shift

config = YAML::load(File.open('config'))

AWS.config(access_key_id: config['access'], secret_access_key: config['secret'], region: 'us-east-1')

s3 = AWS::S3.new

s3.buckets[bucket_name].objects.each_with_index do |obj, i|
  puts obj.key
end

