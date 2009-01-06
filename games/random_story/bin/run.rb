require File.dirname(__FILE__) + "/../src/story"

story_name = ARGV.size != 1 ? "sample.story" : ARGV.shift

story = File.dirname(__FILE__) + "/../example/#{story_name}"

StoryController.new(CLUI.new).play(StoryBuilder.make(story))