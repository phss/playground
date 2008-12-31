require File.dirname(__FILE__) + "/../src/story"

outside = Situation.new(:outside, "You are outside the cave.")
outside.add_action(:enter) { |story| story.go_to(:entrance) }
outside.add_action(:leave) { |story| story.log("Bye bye."); story.end }

entrance = Situation.new(:entrance, "You are at the cave entrance. You see a troll!")
entrance.add_action(:fight) { |story| story.log("The Troll kills you."); story.end }
entrance.add_action(:flee) { |story| story.log("You run away."); story.end }

story = Story.new([outside, entrance])

controller = StoryController.new(CLUI.new)
controller.play(story)