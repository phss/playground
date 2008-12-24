while (n = gets)
  n = n.chomp.to_i
  if (n == 1)
    puts "1"
  else
    puts (n*2-2)
  end
end