require File.dirname(__FILE__) + "/../src/graph"   

# Use DFS to find a cycle in the graph

without_cycle = Graph.from_undirected_edges([1, 2], [1, 3], [2, 4])
with_cycle = Graph.from_undirected_edges([1, 2], [1, 3], [2, 4], [3, 4])

def cycle?(graph)
  graph.dfs(graph.nodes.first) do
    on_any_edge do |search, from, to|
      if !search.undiscovered?(to) && to != search.parent_of(from)
        puts "Cycle from #{from} to #{to}"
        return true
      end
    end
  end
  return false
end

puts cycle?(without_cycle)
puts cycle?(with_cycle)
