# Evaluate arithmetic expressions from graph

Node = Struct.new(:item, :left, :right, :cached)
SUM  = lambda { |x, y| x + y }
DIV  = lambda { |x, y| x / y }
MULT = lambda { |x, y| x * y }

def sum_of(x, y)
  Node.new(SUM, x, y, nil)
end

def div_of(x, y)
  Node.new(DIV, x, y, nil)
end

def mult_of(x, y)
  Node.new(MULT, x, y, nil)
end

def val(x)
  Node.new(x, nil, nil, nil)
end

tree = sum_of(div_of(mult_of(val(3), val(4)), val(5)),
              sum_of(val(2), mult_of(val(3), val(4))))             

intermediate = mult_of(val(3), val(4))
dag = sum_of(sum_of(val(2), intermediate), div_of(intermediate, val(5)))


# Post order interpretation
def calculate(node)
  return node.item if node.left.nil? && node.right.nil?

  if node.cached.nil?
    node.cached = node.item.call(calculate(node.left), calculate(node.right))
  end

  return node.cached
end

puts calculate(tree)
puts calculate(dag)
