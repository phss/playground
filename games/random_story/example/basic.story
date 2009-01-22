story "Basic Story" do

  begin_at :outside
  
  situation :outside do
    description "You are outside a cave. It is raining umbrellas. Watch out!"
    action(:enter) { |story| story.go_to :entrance }
    action(:leave) { |story| story.end_with "Bye, bye." }
  end


  situation :entrance do
    description "You are at the cave entrance. You see a troll!"
    action(:fight) { |story| story.end_with "The Troll kills you." }
    action(:flee)  { |story| story.end_with "You run away." }
  end
end