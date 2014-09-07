require 'yaml'
require 'aws-sdk'
require 'rexml/document'
include REXML

bucket_name = ARGV.shift

config = YAML::load(File.open('config'))

AWS.config(access_key_id: config['access'], secret_access_key: config['secret'], region: 'us-east-1')

s3 = AWS::S3.new

def process(content)
  docsubtype = XPath.first(content, "//xocs:serial-item/simple-article/@docsubtype|//xocs:serial-item/article/@docsubtype")
  title = XPath.first(content, "//xocs:serial-item/simple-article/simple-head/ce:title/text()|//xocs:serial-item/article/head/ce:title/text()")
  have_body = !XPath.first(content, "//body").nil?
  item_weight = XPath.first(content, "//xocs:item-weight/text()")
  item_stage = XPath.first(content, "//xocs:item-stage/text()")

  return [docsubtype, title, have_body, item_weight, item_stage]
end

data = []

s3.buckets[bucket_name].objects.each_with_index do |obj, i|
  if i % 100 == 0
    puts "Object #{i} and up"
  end

  begin
    content = Document.new(obj.read)
    data << ([obj.key] + process(content))
  rescue
    puts "Problem with #{obj.key}. Moving on."
  end
end

File.open("body_stuff.csv", 'w') do |file|
  file.write("\"PII\", \"docsubtype\", \"title\", \"body?\", \"item-weight\", \"item-stage\"\n")
  
  data.each do |d|
    quoted_d = d.map { |i| "\"#{i}\"" }
    file.write(quoted_d.join(", ") + "\n")
  end
end
