require File.dirname(__FILE__) + "/../src/graph"   

# Use DFS to sort a directed acyclic graph topologically

dag = Graph.from_directed_edges([1, 2], [1, 3], [2, 3], [2, 4], [3, 5], [3, 6], [5, 4], [6, 5], [7, 1], [7, 6])

def sort(graph)
  sorted = [] 
  graph.dfs_on_all(graph.nodes) do
    on_node_exit { |search, node| sorted << node }
  end
  return sorted.reverse
end

puts sort(dag).join(" ")
