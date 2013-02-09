require File.dirname(__FILE__) + "/../data_structure/src/graph"

# Does graph have a cycle of length 3?

no_cycle = Graph.from_undirected_edges([1, 2], [2, 3], [3, 4])
three_cycle = Graph.from_undirected_edges([1, 2], [2, 3], [3, 1], [3, 4])
four_cycle = Graph.from_undirected_edges([1, 2], [2, 3], [3, 4], [1, 4], [4, 5])

def cycle_of_3?(graph)
  level = {}
  graph.dfs(1) do
    on_node_entry do |search, node| 
      parent = search.parent_of(node)
      level[node] = (parent.nil? ? 0 : level[parent]) + 1
    end

    on_any_edge do |search, from, to|
      return true if search.edge_type(from, to) == :back && (level[from] - level[to]) == 2
    end
    
  end
  return false
end

puts "No cycle: #{cycle_of_3?(no_cycle)}"
puts "Cycle of 3: #{cycle_of_3?(three_cycle)}"
puts "Cycle of 4: #{cycle_of_3?(four_cycle)}"
