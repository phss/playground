require "sinatra"


get "/" do
  haml :view
end


# Stylesheet link
get "/shared.css" do
   content_type "text/css", :charset => "utf-8"
   sass :shared
end