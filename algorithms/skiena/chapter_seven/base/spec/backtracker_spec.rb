require File.dirname(__FILE__) + "/../src/backtracker" 

describe Backtracker do

  it "should construct all subsets" do
    solutions = []

    Backtracker.generate do
      solution? { |a| a.size == 3 }
      candidates { |a| [true, false] } 
      found_solution do |a| 
        solution = []
        a.each_with_index { |elem, i| solution << i + 1 if elem == true }
        solutions << solution
      end
    end

    solutions.should =~ [[1, 2, 3], [1, 2], [1, 3], [2, 3], [1], [2], [3], []]
  end

  it "should stop when found a solution and return" do
    solutions = []

    Backtracker.generate do
      candidates { |a| a.size < 4 ? [1, 2, 3] : [] }
      solution? { |a| a.first == 2 }
      found_solution do |a|
        solutions << a
        halt_backtracker!
      end
    end

    solutions.should == [[2]]
  end

  it "should generate all permutations" do
    solutions = []

    Backtracker.generate do
      candidates { |a| [1, 2, 3] - a }
      solution? { |a| a.size == 3 }
      found_solution { |a| solutions << a.clone } 
    end

    solutions.should =~ [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
  end

end
