require 'yaml'
require 'aws-sdk'

bucket_name = ARGV.shift
search_term = ARGV.shift

config = YAML::load(File.open('config'))

AWS.config(access_key_id: config['access'], secret_access_key: config['secret'], region: 'us-east-1')

s3 = AWS::S3.new

s3.buckets[bucket_name].objects.each_with_index do |obj, i|
  if i % 100 == 0
    puts "Object #{i} and up"
  end

  content = obj.read

  if content.include?(search_term)
    puts "Key with term #{search_term}: #{obj.key}"
  end
end

puts "Found nothing"
