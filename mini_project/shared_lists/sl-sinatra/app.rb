require 'rubygems'
require 'bundler'
Bundler.require

require_relative 'model'
require_relative 'helpers/date_formatter'

helpers DateFormatter

configure do
  MongoMapper.database = 'shared-events-ruby'

  bootstrap_model  
end

get '/' do
  @user = User.first
  @events_per_day = Event.all.group_by { |event| event.start_at.to_date }

  haml :view
end

# Stylesheet link
get '/shared.css' do
   content_type 'text/css', :charset => 'utf-8'
   sass :shared
end
