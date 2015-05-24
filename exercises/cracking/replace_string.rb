def replace_string(s)
  spaces = s.count(' ')
  original_size = s.size
  s += "*" * spaces * 2
  (original_size-1).downto(0) do |i|
    shift = spaces*2 + i
    if s[i] == ' '
      s[shift]   = '0'
      s[shift-1] = '2'
      s[shift-2] = '%'
      spaces -= 1
      break if spaces.zero?
    else
      s[shift] = s[i]
    end
  end
  return s
end

puts replace_string("ab cd f")
