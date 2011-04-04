require "rubygems"
require "flickraw"

FlickRaw.api_key="key"
FlickRaw.shared_secret="secret"

phrase = "testing"

urls = phrase.split("").collect do |letter|
  photos = flickr.photos.search :per_page => 10, :tags => "#{letter}, oneletter", :tag_mode => "all", :extras => "url_sq"
  photos[rand(photos.size)].url_sq
end

#puts urls

def images(urls)
  urls.inject("") { |html, url| html + "<img src='#{url}' />" }
end


puts """
<html>
  <body>
    #{images(urls)}
  </body>
</html>
"""
