
def switches(engines, queries)
  switches = 0  
  possible_engines = engines.clone

  queries.each do |query|
    possible_engines.delete(query)

    if possible_engines.empty?
      switches += 1
      possible_engines = engines.reject { |e| e == query }
    end
  end
  
  return switches
end


gets.to_i.times do |i|
  engines = []
  queries = []
  gets.to_i.times { |s| engines << gets.chomp }
  gets.to_i.times { |q| queries << gets.chomp }
  puts "Case \##{i+1}: #{switches(engines, queries)}"
end
