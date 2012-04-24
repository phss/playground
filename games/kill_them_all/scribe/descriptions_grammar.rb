DescriptionsGrammar = ProductionGrammar.grammar do

  rule :attack_move, condition { data[:damage] > 0 } do
    one_of([:defender, 'was mauled by', :attack_intensity, :attack, 'for', :damage, 'points by', :attacker])
  end

  rule :attack_move do
    one_of([:defender, :attack_avoidance, :attack_intensity, "attack from", :attacker],
           [:defender, :attack_avoidance, :attacker, "'s", :attack],
           ["In a cunning move,", :defender, "sidestepped", :attacker, "'s", :attack])
  end

  rule :attack_avoidance do
    one_of("blocked", "dodged")
  end

  rule :attack_intensity do
    one_of("a vicious", "a damaging", "an unexpected")
    # "a vicious" | "a damaging" | "an unexpected"
  end

  rule :attack_intensity, condition { data[:damage] >= 5 } do
    "a painful" 
  end

  # Data leafs
  rule :defender do
    data[:defender].name
  end

  rule :attacker do
    data[:attacker].name
  end

  rule :attack do
    data[:attack].to_s
  end

  rule :damage do
    data[:damage]
  end
end