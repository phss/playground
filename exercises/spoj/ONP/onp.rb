class String
  def each_char
    0.upto(self.length-1) { |i| yield self[i].chr}
  end
end

def op?(e)
  %w{+ - * / ^}.include? e
end

gets.chomp.to_i.times do
  exp = gets.chomp
  stack = []
  rpn = ""
  exp.each_char do |c|
    case c
    when ")"
      stack.pop
      rpn << stack.pop if op?(stack.last)
    when "(", "+", "-", "*", "/", "^"
      stack.push(c)
    else
      rpn << c
      rpn << stack.pop if op?(stack.last)
    end
  end
  puts rpn
end