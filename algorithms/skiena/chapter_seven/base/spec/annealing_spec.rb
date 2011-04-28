require File.dirname(__FILE__) + "/../src/annealing"

describe SimulatedAnnealing do

  # Dodgy example, but enough to have some sort of test around it.
  it "should find smallest numbers from set" do
    set = (1..1000).to_a

    solution = SimulatedAnnealing.generate([3, 500, 200, 100, 10], :same_temperature_steps => 2000) do
      cost_function { |a| a.reduce(0) { |res, e| res + e } }
      transition do |a|
        new_a = a.clone
        swap_index = rand(a.size)
        while true
          new_a[swap_index] = set[rand(set.size)]
          break if new_a.uniq.size == a.size
        end
        return new_a
      end
    end

    solution.should =~ [1, 2, 3, 4, 5]
  end

end
