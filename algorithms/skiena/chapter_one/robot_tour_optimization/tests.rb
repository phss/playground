require "test/unit"

require "sequential"
require "nearest_neighbor"

TestInput = Struct.new(:name, :scenario)

class TestAlgorithms < Test::Unit::TestCase

  def setup
    @two_points = TestInput.new("two points", Point.list_for([1.0, 2.0], [3.0, 4.0]))
    @circle_layout =  TestInput.new("circle layout", Point.list_for([0.0, 5.0], [5.0, 0.0], [2.0, -5.0], [-2.0, -5.0], [-5.0, 0.0]))
    @line_layout =  TestInput.new("line layout", Point.list_for([0.0, 0.0], [1.0, 0.0], [-1.0, 0.0], [3.0, 0.0], [-5.0, 0.0], [11.0, 0.0], [-20.0, 0.0]))
  end

  def test_sequential
    puts "\nSequential:"
    run_all_tests_for(Sequential)  
  end

  def test_nearest_neighbor
    puts "\nNearest neighbor:"
    run_all_tests_for(NearestNeighbor)  
  end
  
  private
  
  def run_all_tests_for(algorithm)
    run_and_print_test(algorithm, @two_points)
    run_and_print_test(algorithm, @circle_layout)
    run_and_print_test(algorithm, @line_layout)
  end
  
  def run_and_print_test(algorithm, test_input)
    cycle = algorithm.find_shortest_cycle(test_input.scenario)
    puts "- #{test_input.name}: #{cycle}"
    assert(cycle.closed?, "Cycle should be closed")
  end
  
end