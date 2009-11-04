app_file = File.join(File.dirname(__FILE__), *%w[.. app.rb])
require app_file
require "webrat"
require 'rack/test'

Sinatra::Application.app_file = app_file

World do
  def app
		Sinatra::Application
	end
	include Rack::Test::Methods
	include Webrat::Methods
	include Webrat::Matchers
end

Webrat.configure do |config|
	config.mode = :rack
	config.application_framework = :sinatra
	config.application_port = 4567
end
