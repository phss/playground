class Room
  attr_reader :encrypted_name, :sector_id, :checksum

  def initialize(encrypted_name, sector_id, checksum)
    @encrypted_name = encrypted_name
    @sector_id = sector_id
    @checksum = checksum
  end

  def self.parse(line)
    _, name, sector, checksum = /([a-z|-]+)(\d+)\[([a-z]+)\]/.match(line).to_a.flatten
    Room.new(name, sector.to_i, checksum)
  end

  def calculated_checksum
    letter_frequency = @encrypted_name
                       .chars
                       .reject {|c| c == '-'}
                       .inject(Hash.new(0)) { |freq, letter| freq[letter] += 1; freq }
    letter_frequency.sort_by { |l, f| [-f, l] }.map(&:first)[0..4].join
  end

  def real?
    calculated_checksum == @checksum
  end
end


rooms = File.readlines('files/problem4.txt').map(&:chomp).map { |line| Room.parse(line) }

puts rooms.select(&:real?).map(&:sector_id).reduce(&:+)
