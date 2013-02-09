require File.dirname(__FILE__) + "/../data_structure/src/graph"

# Report traversal order for both graphs

g1 = Graph.from_undirected_edges([:a, :b], [:a, :d], [:a, :i], [:b, :c], [:b, :d], [:b, :e], [:c, :f], [:c, :e], [:d, :e], [:d, :g], [:e, :f], [:e, :g], [:e, :h], [:f, :h], [:g, :h], [:g, :i], [:g, :j], [:h, :j], [:i, :j])

g2 = Graph.from_undirected_edges([:a, :b], [:a, :e], [:b, :c], [:b, :f], [:c, :d], [:c, :g], [:d, :h], [:e, :f], [:e, :i], [:f, :g], [:f, :j], [:g, :h], [:g, :k], [:h, :l], [:i, :j], [:i, :m], [:j, :k], [:j, :n], [:k, :l], [:k, :o], [:l, :p], [:m, :n], [:n, :o], [:o, :p])

def bfs_path(graph)
  path = []
  graph.bfs(:a) do
    on_node_entry { |search, node| path << node }
  end
  return path.join(" ")
end

def dfs_path(graph)
  path = []
  graph.dfs(:a) do
    on_node_entry { |search, node| path << node }
  end
  return path.join(" ")
end

puts "G1 (BFS): #{bfs_path(g1)}" 
puts "G1 (DFS): #{dfs_path(g1)}" 
puts "G2 (BFS): #{bfs_path(g2)}" 
puts "G2 (DFS): #{dfs_path(g2)}" 
