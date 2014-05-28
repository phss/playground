require 'yaml'
require 'aws-sdk'
require 'rexml/document'
include REXML

bucket_name = ARGV.shift
tag = /\<[\w|:|-]+\>/

config = YAML::load(File.open('config'))

AWS.config(access_key_id: config['access'], secret_access_key: config['secret'], region: 'us-east-1')

s3 = AWS::S3.new

unique_tags = []

s3.buckets[bucket_name].objects.each_with_index do |obj, i|
  if i % 100 == 0
    #puts "Object #{i} and up"
  end

  content = obj.read

  unique_tags += content.scan(tag)
  unique_tags.uniq!
end

puts unique_tags.sort
