class Team
  attr_accessor :name, :players
  
  def initialize(name)
    @name = name
    @players = [] 
  end
  
  def self.load_from(file_path)
    team = Team.new(file_path.sub("data/", "").chomp(".data"))
    File.open(file_path, "r") do |f|
      f.each_line do |line|
        team.players << Player.from_raw(line.split("\t"), team)
      end  
    end
    return team
  end
end

class Player
  attr_reader :number, :name, :country, :date_of_birth, :position, :club
  
  def initialize(number, name, country, date_of_birth, position, club)
    @number, @name, @country, @date_of_birth, @position, @club = number, name, country, date_of_birth, position, club
  end
  
  def self.from_raw(raw_data, team)
    Player.new(raw_data.shift, raw_data.shift, team, raw_data.shift, raw_data.shift, Club.from_raw(raw_data.shift))
  end
  
  def to_s
    "#{@name}"
  end

end

class Club
  attr_reader :name, :country
  
  def initialize(name, country)
    @name, @country = name, country
  end
  
  def self.from_raw(club_string)
    matches = /(.+) \((\w+)\)/.match(club_string)
    Club.new(matches[1], matches[2])
  end
  
  def to_s
    "#{@name} (#{@country})"
  end
end

TEAMS = Dir.glob("data/*.data").collect { |file_path| Team.load_from(file_path) }
ALL_PLAYERS = TEAMS.inject([]) { |all, team| all += team.players }.flatten