story "State-based Story" do

  begin_at :outside
  
  situation :outside do
    description "You are outside a cave. It is raining umbrellas. Watch out!"
    action(:enter) { |story| story.log "You carefully enter the cave."; story.go_to :entrance }
    action(:leave) { |story| story.log "Bye, bye."; story.end }
  end

  # TODO add descriptions for different states and entry points.
  situation :entrance do
    description "Although the cave's entrance is dimly lit you can see a Troll sitting in a corner, but it seems to be sleeping. There is a doorway on the other side of the entrance."
    action(:fight) { |story| story.log "Are you crazy? The Troll wake up and kills you."; story.end }
    action(:sneak) { |story| story.log "You tip toe your way to the doorway."; story.go_to :another_room }
    action(:exit) { |story| story.log "You go back outside."; story.go_to :outside }
  end

  situation :another_room do
    description "There is nothing here. :("
    action(:back) { |story| story.go_to :entrance }
  end

end