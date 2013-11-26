require "thor"

class HelloWorld < Thor
  desc "hello NAME", "say hello to NAME"
  def hello(name)
    puts "Hello #{name}"
  end
end

HelloWorld.start(ARGV)
