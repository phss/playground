require File.dirname(__FILE__) + "/../src/annealing"

describe SimulatedAnnealing do

  # Dodgy example, but enough to have some sort of test around it.
  it "should find smallest numbers from set" do
    set = (1..1000).to_a

    solution = SimulatedAnnealing.generate([3, 500, 200, 100, 10], :same_temperature_steps => 10000) do
      delta_cost_function do |a, swap| 
        set[swap.last] - a[swap.first]
      end
      generate_transition do |a|
        a_index = rand(a.size)
        set_index = rand(set.size)
        set_index = rand(set.size) while a.include?(set[set_index])
        return [a_index, set_index]
      end
      transition do |a, swap|
        a[swap.first] = set[swap.last]
      end
    end

    solution.should =~ [1, 2, 3, 4, 5]
  end

end
