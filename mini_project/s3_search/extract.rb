require 'yaml'
require 'aws-sdk'
require 'rexml/document'
include REXML

bucket_name = ARGV.shift
DESTINATION = ARGV.shift
tag = /<([\/]?[\w|:|-]+).*?(\/)?>/


def process(key, tags)
  level = []

  File.open("#{DESTINATION}/#{key}.txt", 'w') do |file|
    previous_tag = ""
    tags.each do |tag_match|
      tag = tag_match.chomp
      close_tag = tag.start_with?("/")
      self_close_tag = tag.end_with?("/")

      if close_tag
        level.pop
      elsif self_close_tag
        level << tag
        file.write(level.join("/") + "\n")
        level.pop
      else
        level << tag
        file.write(level.join("/") + "\n")
      end
    end
  end
end

config = YAML::load(File.open('config'))

AWS.config(access_key_id: config['access'], secret_access_key: config['secret'], region: 'us-east-1')

s3 = AWS::S3.new

unique_tags = []

s3.buckets[bucket_name].objects.each_with_index do |obj, i|
  if i % 100 == 0
    puts "Object #{i} and up"
  end

  content = obj.read

  tags = content.scan(tag).map(&:join)
  process(obj.key, tags)
end

