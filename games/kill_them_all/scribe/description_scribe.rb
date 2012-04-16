class DescriptionScribe

  def initialize(description_file)
    @descriptions = {}
    instance_eval File.read(description_file), description_file 
  end

  def describe(description, &blk)
    @descriptions[description] = blk
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

  # Invoking description
  def generate(description, d)
    @data = d
    instance_eval &@descriptions[description]
  end
  
 private
  def interpret(element) 
    if element.kind_of?(Symbol)
      generate(element, data)
    else
      element
    end
  end

end