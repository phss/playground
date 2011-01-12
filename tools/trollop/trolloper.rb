require "rubygems"
require "trollop"

opts = Trollop::options do
  opt :test, "Required test flag", :type => String
  opt :optional, "Optional flag", :type => String, :default => "Optional value"
  opt :bool, "Boolean flag"
end
Trollop::die :test, "must exist" unless opts[:test]

puts opts
