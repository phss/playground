
def switches(engines, queries)
  2  
end


gets.to_i.times do |i|
  engines = []
  queries = []
  gets.to_i.times { |s| engines << gets }
  gets.to_i.times { |q| queries << gets }
  puts "Case \##{i+1}: #{switches(engines, queries)}"
end
