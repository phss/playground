# Given a crossword solution as a two dimensional array, find all the words
# that are not in the dictionary

DICTIONARY = %w{AA CF CE ABCD AB CB CEC}

def in_dictionary?(word)
  DICTIONARY.include?(word)
end

def words_from(matrix)
  matrix.flat_map do |row|
    row.join.split.reject { |word| word.size < 2 }
  end
end

def invalid_crossword_words(solution)
  all_words = words_from(solution) + words_from(solution.transpose)
  all_words.reject { |word| in_dictionary?(word) }
end


# All valid
puts "All valid"
valid_crossword_solution = [
  ['A', 'A', ' ', ' '],
  ['B', ' ', 'C', 'F'],
  [' ', 'C', 'E', ' '],
  ['A', 'B', 'C', 'D']
]
puts invalid_crossword_words(valid_crossword_solution) # => []

# Some invalid
puts "\nSome invalid"
some_invalid_crossword_solution = [
  ['A', 'A', ' ', ' '],
  ['X', ' ', 'C', 'Z'],
  [' ', 'C', 'Y', ' '],
  ['A', 'B', 'C', 'D']
]
puts invalid_crossword_words(some_invalid_crossword_solution) # => [AX CYC CZ CY]
