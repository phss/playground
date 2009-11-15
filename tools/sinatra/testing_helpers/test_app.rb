require 'app'
require 'test/unit'
require 'rack/test'

set :environment, :test

class HelperTest < Test::Unit::TestCase
  include Rack::Test::Methods
  include MyHelpers

  def app
    Sinatra::Application
  end

  def test_it_can_access_the_helper
    assert_equal "Hello from the helper", message_from_helper
  end

end