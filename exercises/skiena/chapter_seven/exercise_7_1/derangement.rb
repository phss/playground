require File.dirname(__FILE__) + "/../base/src/backtracker"
require "benchmark"

# Create a derrangement of the first N elements

def naive(n)
  all = (0..n-1).to_a
 
  solutions = []

  Backtracker.generate do
    candidates { |a|  all - a }
    solution? do |a| 
      a.each_with_index { |e, i| return false if i == e  } 
      a.size == n
    end
    found_solution { |a| solutions << a.clone }
  end

  return solutions
end

def prunning(n)
  all = (0..n-1).to_a
 
  solutions = []

  Backtracker.generate do
    candidates do |a| 
      remaining = all - a
      index = a.size
      remaining - [index]     
    end
    solution? { |a| a.size == n }
    found_solution { |a| solutions << a.clone }
  end

  return solutions
end


#naive(4).each do |s| 
#  puts s.join(" ")
#end

NUM = 8

Benchmark.bm(10) do |x|
  1.upto(NUM) do |i|
    x.report("Naive: -> #{i}") { naive(i) }
    x.report("Prune: -> #{i}") { prunning(i) }
  end
end
