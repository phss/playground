require File.dirname(__FILE__) + "/../src/graph"   

# Use BFS to color a bipartite graph

graph = Graph.from_undirected_edges([1, 2], [1, 3], [2, 4], [2, 5], [3, 4], [3, 5], [4, 6], [5,6])#, [1, 4]) # Uncomment to test non-bipartite
colors = graph.nodes.reduce({}) { |res, node| res[node] = :uncolored; res }

colors[1] = :white
graph.bfs(1) do
  on_any_edge do |search, from, to|
    raise "No bipartite!" if colors[from] == colors[to]
    colors[to] = colors[from] == :white ? :black : :white
  end
end

puts colors
