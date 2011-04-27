# Implements the backtracker as described by the book

class Backtracker

  def initialize(&block)
    @hooks = BacktrackerHooks.from(&block)
    @array = []
  end

  def Backtracker.generate(&block)
    backtracker = Backtracker.new(&block)
    backtracker.backtrack()
  end

  def backtrack
    if solution?
      found_solution
    else
      candidates.each do |candidate|
        generate_next candidate          
        return if halt?
        undo_last_candidate
      end
    end

  end

  private 

  def generate_next(candidate)
    @array << candidate
    next_move
    backtrack
  end

  def undo_last_candidate
    @array.pop
    undo_move
  end

  def halt?
    @hooks.halt?
  end

  def method_missing(m, *args, &block)
    hook_method_name = "hook_#{m.to_s}"
    super unless @hooks.respond_to? hook_method_name
    @hooks.send(hook_method_name, @array)
  end

end

class BacktrackerHooks

  def initialize
    @should_halt = false
  end

  def self.from(&block)
    hooks = BacktrackerHooks.new
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

  def halt_backtracker!
    @should_halt = true
  end

  def halt?; @should_halt; end

  # Blank implementations of the hook methods
  def hook_solution?(a); end
  def hook_candidates(a); []; end
  def hook_found_solution(a); end
  def hook_next_move(a); end
  def hook_undo_move(a); end

end
