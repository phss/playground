class DodgedDescription
  DESCRIPTIONS = [
    "-defender- dodged a vicious -attack- from -attacker-.",
    "-defender- blocked -attacker-'s -attack-.",
    "In a cunning move, -defender- sidestepped -attacker-'s -attack-."
  ]

  def self.for(attacker, defender, attack_type)
    description(attacker.name, defender.name, attack_type.to_s)
  end

 private

  def self.description(attacker, defender, attack)
    template = DESCRIPTIONS[rand(DESCRIPTIONS.size)]

    template.gsub!(/-(.*?)-/) { '#{'+$1+'}' }
    eval('"' + template + '"')
  end
end