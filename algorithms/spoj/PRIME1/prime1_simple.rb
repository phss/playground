#require "profile"

MAX = Math.sqrt(1000000000).to_i

primes = []
bit_primes = Array.new(MAX+1, true)
bit_primes[0] = false
bit_primes[1] = false

bit_primes.each_with_index do |is_prime, i|
  if is_prime
    primes << i
    multi_index = 2
    while (j = multi_index*i) <= MAX
      bit_primes[j] = false
      multi_index += 1
    end
  end
end

t = gets.chomp.to_i
t.times do |i|
  m, n = gets.chomp.split(" ")
  
  m.to_i.upto(n.to_i) { |j| puts j if primes.include? j }
  
  puts "" unless i == t-1
end