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

header_string = header.map do |val|
  "<th>#{val}</th>" 
end.join

rows_string = rows.map do |row|
  row_string = row.map do |val|
    "<td>#{val}</td>" 
  end.join
  "<tr>#{row_string}</tr>"
end.join

puts <<-eos
<html>
  <body>
    <table>
      <tr>#{header_string}</tr>
      #{rows_string}
    </table>
  </body>
</html>
eos
