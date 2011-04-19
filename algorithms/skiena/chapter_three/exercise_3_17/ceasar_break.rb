# Generates a Ceasar Cipher and breaks it! It assumes the letter 'e' has the highest occurence

LETTERS = ('a'..'z').to_a
SHIFT = rand(LETTERS.size)

def encode(message, shift)
  message.split("").collect do |char|
   if LETTERS.include? char
      LETTERS[(char.ord - ?a.ord + shift)%LETTERS.size]
    else
      char
    end
  end.join
end

# Original
original = File.open("text.txt", "r").read.downcase

# Encoded
encoded = encode(original, SHIFT)

# Decoding
letter_occurrences = Array.new(LETTERS.size) { 0 }

encoded.each_char { |char| letter_occurrences[char.ord - ?a.ord] += 1 if LETTERS.include? char }

index_of_e = 0
letter_occurrences.each_with_index { |entry, i| index_of_e = i if entry > letter_occurrences[index_of_e]  }

reverse_shift = -index_of_e + 4
decoded = encode(encoded, reverse_shift)
puts decoded
