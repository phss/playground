# Identifies matching parenthisis
class ParenthisisMatcher

  def self.match(expression)
    stack = []
    expression.each_char do |char|
      if char == "("
        stack.push(char)
      elsif char == ")"
        return false if stack.empty?
        stack.pop
      end
    end
    stack.empty?
  end

end

