require 'rubygems'
require 'bundler'
Bundler.require

require_relative 'model'
require_relative 'helpers/date_formatter'

helpers DateFormatter

configure do
  MongoMapper.database = 'shared-events-ruby'
  enable :sessions

  bootstrap_model  
end

get '/' do
  redirect '/login' unless session['user']

  @user = User.where(:name => session['user']).first
  @events_per_day = Event.participating(@user).group_by { |event| event.start_at.to_date }

  haml :view
end

get '/login' do
  @available_users = User.all

  haml :login
end

post '/login' do
  session['user'] = params[:login_as]
  redirect '/'
end

get '/logout' do
  session['user'] = nil
  redirect '/'
end

# Stylesheet link
get '/shared.css' do
   content_type 'text/css', :charset => 'utf-8'
   sass :shared
end
