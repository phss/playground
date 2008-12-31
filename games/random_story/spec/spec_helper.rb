require File.dirname(__FILE__) + '/../src/story'

SAMPLE_SITUATION = Situation.new(:sample_name, "Sample description")
SAMPLE_SITUATION.add_action(:sample_action) { |story| }