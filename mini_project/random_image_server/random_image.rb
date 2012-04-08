require "rubygems"
require "sinatra"
require "haml"

configure do
  set :public, "."
end

get "/random" do
  content_type "image/*"
  images = Dir["images/*"]
  File.open(images[rand(images.size)], "r").read
end

get "/sample" do
  "<img src='/random' />"
end

get "/" do
  @images = Dir["images/*"]
  haml :main
end

post "/add_image" do
  File.open("images/#{params[:image][:filename]}", "wb") do |file|
    file.write(params[:image][:tempfile].read)
  end
  redirect "/"
end