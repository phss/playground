my_number = rand(10) + 1

puts 'Can you guess my number? Pick a number from 1 to 10'

number = gets.chomp.to_i

if number == my_number
  puts "You got it!"
else
  puts "Nope. My number is #{my_number}."
end
