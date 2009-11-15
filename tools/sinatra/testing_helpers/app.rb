require "rubygems"
require "sinatra"
require "helpers"

helpers MyHelpers

get "/" do
  message_from_helper
end