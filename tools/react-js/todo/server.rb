require 'sinatra'
require 'json'

@@data = [
  {id: 1, task: "Don't hard code data", status: "open"},
  {id: 2, task: "Finish everything", status: "open"}
]

get '/' do
  send_file File.join(settings.public_folder, 'index.html')
end

get '/api/list' do
  content_type :json
  @@data.to_json
end

post '/api/new' do
  new_entry = JSON.parse(request.body.read)
  @@data << new_entry
  "created"
end
