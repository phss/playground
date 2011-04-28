require File.dirname(__FILE__) + "/../base/src/backtracker"
require File.dirname(__FILE__) + "/../../chapter_five/data_structure/src/graph"
require "benchmark"

# Backtrack solution to the bandwith minimization problem

MAX_INT = 1000 # not reaaaaally a max

def total_cost(graph, solution)
  max_length = 0

  solution.each_with_index do |node, i| 
    graph.edges_for(node).each do |to| 
      j = solution.index to
      length = (i - j).abs
      max_length = length if length > max_length
    end
  end

  return max_length
end

def next_cost(graph, solution, element)
  next_spot = solution.size
  max_length = 0

  graph.edges_for(element).each do |to|
    j = solution.index to
    if j
      length = (next_spot - j).abs
      max_length = length if length > max_length
    end
  end

  return max_length
end

def bandwith(graph)
  nodes = graph.nodes
  current = nil
  current_length = MAX_INT
  
  Backtracker.generate do
    candidates do |a|  
      (nodes - a).reject { |node| next_cost(graph, a, node) > current_length }
    end
    solution? { |a| a.size == nodes.size }
    found_solution do |a| 
      current = a.clone
      current_length = total_cost(graph, current)
    end
  end

  return current
end


# Tests

def dense_graph(number_of_nodes)
  edges = []
  1.upto(number_of_nodes-1) do |i|
    (i+1).upto(number_of_nodes) do |j|
      edges << [i, j]
    end
  end
  return Graph.from_undirected_edges(*edges)
end

graphs = {
  :line => Graph.from_undirected_edges([4, 6], [2, 7], [2, 8], [3, 7], [3, 6], [4, 5], [1, 8]),
  :dense => dense_graph(8),
  :almost_dense => Graph.from_undirected_edges([1, 2], [1, 3], [1, 4], [1, 5], [1, 6], [2, 3], [2, 4], [2, 5], [2, 6], [3, 4], [3, 5], [3, 6], [4, 6], [5, 6]),
}

#puts bandwith(graphs[:dense]).join(" ")


Benchmark.bm(10) do |x|
  graphs.each do |k, v|
    x.report("#{k}") { bandwith(v) }
  end
end
