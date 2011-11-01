a = (1..16).to_a

def print_with_each(array)
  buffer = []
  array.each do |item|
    buffer << item
    if buffer.size == 4
      puts buffer.join(" ")
      buffer = []
    end
  end
end

def print_with_slice(array)
  array.each_slice(4) { |items| puts items.join(" ") } 
end


print_with_each a
print_with_slice a
