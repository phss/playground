require "prime"
require "set"

prime_sets = []

Prime.each do |prime|
  puts prime
  
  exit if prime > 100  
end
