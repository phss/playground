# Uniqueness of an array

array = [1, 2, 3, 5] * 10

# O(n log n)
def sort_uniq(a)
  sorted = a.sort # Handwaving sorting
  unique = []

  sorted.each { |e| unique << e if e != unique.last  }

  return unique
end

# O(2n + m) where m is size of hash
def hash_uniq(a)
  unique = {}

  a.each { |e| unique[e] = e unless unique.key? e }
  
  unique.keys
end

puts sort_uniq(array).join(" ")
puts hash_uniq(array).join(" ")
