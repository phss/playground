#require "profile"

MAX = Math.sqrt(1000000000).to_i
PRIMES = []

def is_prime(n)
  return PRIMES.include?(n) if n <= PRIMES.last
  
  PRIMES.find { |p| n%p == 0 } == nil
end

def sub_primes(from, to)
  return PRIMES if to <= PRIMES.last
  
  primes = []
  bit_primes = Array.new(to - from, true)

  bit_primes.each_with_index do |is_prime, i|
    if is_prime
      PRIMES << i
      multi_index = 2
      while (j = multi_index*i) <= MAX
        bit_primes[j] = false
        multi_index += 1
      end
    end
  end
  
  return primes
end

bit_primes = Array.new(MAX+1, true)
bit_primes[0] = false
bit_primes[1] = false

bit_primes.each_with_index do |is_prime, i|
  if is_prime
    PRIMES << i
    multi_index = 2
    while (j = multi_index*i) <= MAX
      bit_primes[j] = false
      multi_index += 1
    end
  end
end

t = gets.chomp.to_i
t.times do |i|
  m, n = gets.chomp.split(" ").collect { |n| n.to_i }
  
  primes = sub_primes(m, n)
  
  m.upto(n) { |j| puts j if PRIMES.include?(j) }  
  # m.upto(n) { |j| puts j if is_prime(j) }    
  
  puts "" unless i == t-1
end