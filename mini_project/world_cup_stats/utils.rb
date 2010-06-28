def print_breakdown(breakdown)
  breakdown.sort { |a, b| b[1] <=> a[1] }.each do |breakdown|
    puts "- #{breakdown[0]}: #{breakdown[1]}"
  end
end