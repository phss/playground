require "rubygems"
require "flickraw"

FlickRaw.api_key="not here"
FlickRaw.shared_secret="not here"

list = flickr.photos.getRecent :extras => "tags=dog"

list.each do |photo|
  info = flickr.photos.getInfo :photo_id => photo.id, :secret => photo.secret
  puts info.urls.first
end
