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

get '/api/tasks' do
  content_type :json
  @@data.to_json
end

post '/api/tasks/new' do
  content_type :json
  new_task = JSON.parse(request.body.read)
  new_task[:id] = @@next_id
  new_task[:status] = 'open'
  @@next_id += 1
  @@data << new_task
  new_task.to_json
end

put '/api/tasks/:id/:status' do
  content_type :json
  task_id = params[:id].to_i
  task = @@data.find {|d| d[:id] == task_id}
  task[:status] = params[:status]
  task.to_json
end

delete '/api/tasks/:id' do
  content_type :json
  task_id = params[:id].to_i
  @@data.reject! {|d| d[:id] == task_id}
  @@data.to_json
end
