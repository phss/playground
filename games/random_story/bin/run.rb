require File.dirname(__FILE__) + "/../src/story"

sample_story = File.dirname(__FILE__) + "/../example/sample.story"

StoryController.new(CLUI.new).play(StoryBuilder.make(sample_story))