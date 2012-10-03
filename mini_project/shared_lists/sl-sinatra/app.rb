require "sinatra"


get "/" do
  "This has nothing...for now"
end

get "/has-a-view" do
  haml :view
end