require "rubygems"
require 'date'
require 'sinatra'
require_relative 'model'
require_relative 'helpers/date_formatter'

helpers DateFormatter

get '/' do
  @user = USERS.first
  @events_per_day = EVENTS.group_by { |event| event.start_at.to_date }

  haml :view
end

# Stylesheet link
get '/shared.css' do
   content_type 'text/css', :charset => 'utf-8'
   sass :shared
end
