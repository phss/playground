filename = ARGV[0]
phrase = ARGV[1]

File.open(filename) do |file|
  file.lines do |line|
    puts "#{file.lineno} -> #{line}" if line.include? phrase
  end
end
