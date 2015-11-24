require 'sinatra'
require 'json'

@@data = [
  {id: 1, task: "Don't hard code data"},
  {id: 2, task: "Finish everything"}
]

get '/' do
  send_file File.join(settings.public_folder, 'index.html')
end

get '/api' do
  content_type :json
  @@data.to_json
end
