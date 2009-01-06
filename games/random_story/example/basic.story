story "Sample Story" do

  begin_at :outside
  
  situation :outside do
    description "You are outside a cave. It is raining umbrellas. Watch out!"
    action(:enter) { |story| story.go_to :entrance }
    action(:leave) { |story| story.log "Bye, bye."; story.end }
  end


  situation :entrance do
    description "You are at the cave entrance. You see a troll!"
    action(:fight) { |story| story.log "The Troll kills you."; story.end }
    action(:flee) { |story| story.log "You run away."; story.end }
  end
end