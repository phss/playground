require "rubygems"
require 'date'
require 'sinatra'
require_relative 'model'

helpers do
  def day_and_month(date)
    date.strftime("%d/%m")
  end

  def week_day(date)
    date.strftime("%A")
  end

  def time_of(event)
    time = event.start_at.strftime("%H:%M")
    if event.end_at
      time += " to #{event.end_at.strftime('%H:%M')}"
    end
    return time
  end
end

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
