# Check if all letters from a search string are contained in a source string. Solution is simplified a bit, expecting only letters in the strings

def check(search, source)
  remaining_letters = Array.new(?z.ord - ?a.ord + 1) { 0 }
  search.split("").each { |char| remaining_letters[char.ord - ?a.ord] += 1 }
  remaining_total_letters = search.size

  source.split("").each do |char|
    index = char.ord - ?a.ord
    remaining_letters[index] -= 1 and remaining_total_letters -= 1 if remaining_letters[index] > 0
    break if remaining_total_letters == 0
  end

  return remaining_total_letters == 0
end


puts check("abaaad", "bdasdeafaklada")
puts check("x", "bdasdeafaklada")
