# Simulated annealing algorithm

class SimulatedAnnealing

  DEFAULT_OPTIONS = {
    :initial_temperature => 1,
    :cooling_steps => 3,
    :same_temperature_steps => 1000,
    :temperature_decrement => 0.8,
    :initial_cost => 0
  }

  def initialize(initial_solution, &block)
    @hooks = AnnealingHooks.from(&block)
    @solution = initial_solution
  end

  def SimulatedAnnealing.generate(initial_solution, options = {}, &block)
    annealing = SimulatedAnnealing.new(initial_solution, &block)
    annealing.calculate(DEFAULT_OPTIONS.merge(options))
  end
  
  def calculate(options)
    temperature = options[:initial_temperature]
    current_cost = options[:initial_cost]

    options[:cooling_steps].times do
      options[:same_temperature_steps].times do
        change = @hooks.hook_generate_transition(@solution)
        delta = @hooks.hook_delta_cost_function(@solution, change)
        
        if delta < 0 || (@hooks.hook_acceptance_function(delta, temperature) > rand)
          current_cost += delta
          @hooks.hook_transition(@solution, change)
        end
      end

      temperature *= options[:temperature_decrement]
    end
   
    return @solution
  end

end

class AnnealingHooks

  def self.from(&block)
    hooks = AnnealingHooks.new
    hooks.instance_eval &block unless block.nil?
    return hooks
  end

  def method_missing(m, *args, &block)
    hook_method_name = "hook_#{m.to_s}"
    super unless respond_to? hook_method_name
    (class << self; self; end).class_eval do
      define_method hook_method_name, &block 
    end
  end

  # Default acceptance function
  def hook_acceptance_function(delta, temperature)
    1/(1 + Math::exp(delta/temperature.to_f))
  end

  # Blank implementations of the hook methods
  def hook_delta_cost_function(a, swap); 0; end
  def hook_generate_transition(a); []; end
  def hook_transition(a, swap);  end

end
