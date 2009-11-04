require "rubygems"
require "sinatra"
require "haml"

get "/" do
  "Hello World"
end

get "/with_views" do
  haml :some_view
end
