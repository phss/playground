require 'sinatra'
require 'json'

# Data storage is definitely not threadsafe
@@data = [
  {id: 1, task: "Don't hard code data", status: "open"},
  {id: 2, task: "Finish everything", status: "open"}
]
@@next_id = @@data.map {|d| d[:id]}.max + 1

get '/' do
  send_file File.join(settings.public_folder, 'index.html')
end

get '/api/list' do
  content_type :json
  @@data.to_json
end

post '/api/new' do
  content_type :json
  new_entry = JSON.parse(request.body.read)
  new_entry[:id] = @@next_id
  new_entry[:status] = 'open'
  @@next_id += 1
  @@data << new_entry
  new_entry.to_json
end
