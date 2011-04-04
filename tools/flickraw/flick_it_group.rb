require "rubygems"
require "flickraw"

FlickRaw.api_key="keu"
FlickRaw.shared_secret="secret"

groups = flickr.groups.search(:text => "One Digit")

group = groups.first

puts group.name
puts group.nsid

info = flickr.groups.getInfo :group_id => group.nsid

puts info.description
