csv_file=ARGV[0]

header = []
rows = []

# Not how to actually parse a CSV
File.open(csv_file, "r") do |f|
  process_header = true
  f.each_line do |line|
    values = line.split(',').map(&:chomp)
    if process_header
      header = values
      process_header = false
    else
      rows << values
    end
  end
end

print header
print rows
