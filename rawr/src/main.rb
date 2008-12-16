require "java"
 
$CLASSPATH << File.expand_path(File.dirname(__FILE__) + "/../lib/slick.jar")
$CLASSPATH << File.expand_path(File.dirname(__FILE__) + "/../lib/lwjgl.jar")
$LOAD_PATH << File.expand_path(File.dirname(__FILE__) + "/../ruby")

module Slick
  include_package "org.newdawn.slick"
  include_package "org.newdawn.slick.util"  
end

#require File.expand_path(File.dirname(__FILE__) + "/ruby/game_window")
require "game_window"

puts "Testing Rawr"

container = Slick::AppGameContainer.new(GameWindow.new)
container.set_display_mode(340, 440, false)
container.set_target_frame_rate(30)
container.set_minimum_logic_update_interval(30)
container.set_show_fps(false)
container.set_verbose(false)
container.start