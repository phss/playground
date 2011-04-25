require File.dirname(__FILE__) + "/../data_structure/src/graph"

# Number of shortest paths

mother = Graph.from_directed_edges([1, 2], [2, 3], [2, 4], [3, 5], [3, 6], [4, 6], [6, 7])
no_mother = Graph.from_directed_edges([1, 2], [2, 3], [4, 5])

def mother?(graph, start)
  search = graph.bfs(start)
  graph.nodes.all? { |node| !search.undiscovered? node }
end

def have_mother(graph)
  search = graph.bfs_on_all(graph.nodes)
  graph.nodes.find_all { |node| search.parent_of(node).nil? }.size == 1
end

puts "Should have mother: " + have_mother(mother).to_s
puts "Right mother: " + mother?(mother, 1).to_s
puts "Wrong mother: " + mother?(mother, 4).to_s
puts
puts "No mother: " + have_mother(no_mother).to_s
