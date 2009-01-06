story "Longish" do

  begin_at :outside
  
  situation :outside do
    description "You are outside a cave. It is raining umbrellas. Watch out!"
    action(:enter) { |story| story.go_to :entrance }
    action(:leave) { |story| story.log "Bye, bye."; story.end }
  end

end