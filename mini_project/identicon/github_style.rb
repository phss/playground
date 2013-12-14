
def print(identicon)
  identicon.each do |line|
    puts line.join
  end
end

def block
  is_on = [true, false].sample
  return is_on ? '#' : ' '
end

def generate
  (0..5).map do |line|
    a, b, c = block, block, block
    [a, b, c, b, a]
  end
end

print(generate)
