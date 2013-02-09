require File.dirname(__FILE__) + "/../src/graph"   

# Use BFS to discover number of components

graph = Graph.from_undirected_edges([1, 2], [2, 3], [1, 3], [4, 5])
components = 1 # starts at 1 since we do a initial search on the graph

searcher = graph.bfs(1)
graph.nodes.each do |node| 
  if searcher.undiscovered?(node) 
    searcher.search(node)
    components += 1
  end
end

puts components

