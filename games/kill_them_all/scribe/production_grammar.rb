class ProductionGrammar

  def initialize
    @descriptions = {}
  end

  def self.grammar(&blk)
    grammar = ProductionGrammar.new
    grammar.instance_eval &blk
    grammar
  end

  def describe(description, conditions = {}, &blk)
    entry = { :conditions => conditions, :action => blk}
    @descriptions[description] ||= []
    @descriptions[description] << entry
  end

  def one_of(*options)
    option = options[rand(options.size)]
    if option.kind_of?(Array)
      option.collect { |element| interpret(element) }.join(' ')
    else
      interpret(option)
    end
  end

  def data
    @data
  end

  def condition(&blk)
    { :when => blk }
  end

  # Invoking description
  def generate(description, d)
    @data = d
    instance_eval &entry_for(description)[:action]
  end
  
 private
  def interpret(element) 
    if element.kind_of?(Symbol)
      generate(element, data)
    else
      element
    end
  end

  def entry_for(description)
    descriptions = @descriptions[description]
    with_condition = lambda { |desc| desc[:conditions].has_key?(:when) }

    conditional_descriptions = descriptions.select &with_condition
    unconditional_descriptions = descriptions.reject &with_condition

    passing_conditions = conditional_descriptions.select do |desc| 
      action = desc[:conditions][:when]
      action.call
    end

    if passing_conditions.empty?
      unconditional_descriptions.first
    else
      passing_conditions.first
    end
  end 
end