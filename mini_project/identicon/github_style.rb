
def print(identicon)
  identicon.each do |line|
    puts line.join
  end
end

print([
  ['#', ' ', ' ', '#'],
  ['#', ' ', ' ', '#'],
  ['#', ' ', ' ', '#'],
  ['#', '#', '#', '#']
])
