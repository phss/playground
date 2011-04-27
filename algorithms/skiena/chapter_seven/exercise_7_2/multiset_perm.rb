require File.dirname(__FILE__) + "/../base/src/backtracker"
require "benchmark"

# Create a multiset permutations

def naive(set)
  solutions = []
  indexes = (0..set.size-1).to_a

  Backtracker.generate do
    candidates { |a|  indexes - a }
    solution? { |a| a.size == set.size }
    found_solution do |a| 
      solution = a.map { |i| set[i] } 
      solutions << solution unless solutions.include? solution
    end
  end

  return solutions
end

def prunning(set)
  solutions = []
  multiset = Hash.new(0)
  set.each { |e| multiset[e] += 1 }

  Backtracker.generate do
    candidates do |a|  
      multiset.keys.reject { |key| multiset[key] == 0 }
    end
    next_move { |a| multiset[a.last] -= 1 } 
    undo_move { |a| multiset[a.last] += 1 } 
    solution? { |a| a.size == set.size }
    found_solution { |a| solutions << a.clone }
  end

  return solutions

end


#prunning([1, 1, 2, 2]).each do |s| 
#  puts s.join(" ")
#end

NUM = 9
base = [1, 2]

Benchmark.bm(10) do |x|
  1.upto(NUM) do |i|
#    x.report("Naive: -> #{i}") { naive(base * i) }
    x.report("Prune: -> #{i}") { prunning(base * i) }
  end
end
