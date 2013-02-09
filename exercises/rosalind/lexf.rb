
def lexo_strings(symbols, n)
  strings = symbols

  while n > 1
    strings = strings.reduce([]) do |new_strings, string|
      new_strings.concat(symbols.map { |sym| string + sym  })
    end
    n -= 1
  end

  return strings
end

syms = "R Z O B A L Y".split(' ')
n = 3

puts lexo_strings(syms, n)
