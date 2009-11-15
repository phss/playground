require "rubygems"
require "dm-core"

class Stuff
  include DataMapper::Resource
  
  property :id, Serial
  property :original_field, String
  # property :other_field, String, :nullable => false
end

DataMapper.setup(:default, "sqlite3://#{Dir.pwd}/stuff.sqlite3")
Stuff.auto_migrate!
# Stuff.auto_upgrade!


Stuff.new(:original_field => "stuff").save
Stuff.all.each { |s| puts s.attributes }