require File.dirname(__FILE__) + "/../base/src/backtracker"
require File.dirname(__FILE__) + "/../../chapter_five/data_structure/src/graph"
require "benchmark"

# Backtrack solution to the satisfiablilty (in the correct spelling) problem

class Sat
  attr_reader :clauses

  def initialize(max)
    @max = max
    @clauses = []
  end

  def Sat.load(filename)
    lines = File.open(filename, "r").readlines.collect { |l| l.split(" ").collect { |c| c.chomp.to_i } }
    header = lines.shift
    sat = Sat.new(header.first)
    lines.each { |line| sat.with(*line) }
    return sat
  end

  def with(*entries)
    clause = Array.new(@max) { :any }
    entries.each do |e|
      clause[e.abs-1] = e < 0 ? :off : :on
    end
    @clauses << clause
    self
  end

end

def solution_so_far(clauses, solution)
  return clauses.all? do |clause|
    satisfied = true
    solution.each_with_index do |k, i|
      next if clause[i] == :any
      if k == clause[i]
        satisfied = true
        break
      else
        satisfied = false
      end
    end
    satisfied
  end
end

def sat(clauses)
  solution = nil

  Backtracker.generate do
    candidates do |a|  
      #c = clauses.map { |clause| clause[a.size] }.uniq - [:any]     
      #c = [:on, :off] if c.empty?
      c = [:on, :off].reject { |blah| !solution_so_far(clauses, a + [blah]) }
      puts c.to_s
      c
    end

    solution? do |a|
      a.size == clauses.first.size
    end

    found_solution do |a| 
      if solution_so_far(clauses, a) 
        solution = a
        halt_backtracker!
      end
    end
  end

  return solution
end


# Tests
examples = {
  :sample => Sat.new(3).with(1, 2, -3).with(-1, -2, 3).with(-1, -2, -3).with(-1, 2, 3).clauses,
  :another => Sat.new(3).with(1, -2).with(2, 3).clauses,
  :unsolvable => Sat.new(2).with(1, 2).with(-1, 2).with(-2, 1).with(-1, -2).clauses,
  :larger => Sat.new(8).with(1, 4).with(-2, 5).with(3, 7).with(2, -5).with(-8, -2).with(3, -1).with(4, -3).with(5, -4).with(-3, -7).with(6, 7).with(1, 7).with(-7, -1).clauses,
  :slow => Sat.load("test.txt").clauses
 # :hard2sat => Sat.load("hard2sat.txt").clauses
}
puts sat(examples[:sample]).to_s
puts sat(examples[:larger]).to_s
puts sat(examples[:slow]).to_s

Benchmark.bm(10) do |x|
  examples.each do |k, v|
    x.report("#{k}") { sat(v) }
  end
end
