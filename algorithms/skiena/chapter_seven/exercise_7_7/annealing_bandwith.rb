require File.dirname(__FILE__) + "/../base/src/annealing"
require File.dirname(__FILE__) + "/../../chapter_five/data_structure/src/graph"
require "benchmark"

# Simulated annealing solution to the bandwith minimization problem

def total_cost(graph, solution)
  max_length = 0
  preindex = solution.reduce({}) { |res, e| res[e] = solution.index(e); res}

  solution.each_with_index do |node, i| 
    graph.edges_for(node).each do |to| 
      j = preindex[to]
      length = (i - j).abs
      max_length = length if length > max_length
    end
  end

  return max_length
end

def cost(graph, a, swap)
  i, j = swap.first, swap.last
  max_current = [node_max_cost(graph, a, i), node_max_cost(graph, a, j)].max 
  a[i], a[j] = a[j], a[i]
  max_transition = [node_max_cost(graph, a, i), node_max_cost(graph, a, j)].max 
  a[i], a[j] = a[j], a[i]
  
  return max_transition - max_current
end

def node_max_cost(graph, solution, i)
  max_length = 0 
  graph.edges_for(solution[i]).each do |to| 
    j = solution.index(to)
    length = (i - j).abs
    max_length = length if length > max_length
  end
  return max_length
end

def bandwith(graph)
  SimulatedAnnealing.generate(graph.nodes, :same_temperature_steps => 1000, :cooling_steps => 300) do
    delta_cost_function { |a, swap| cost(graph, a, swap) }

    generate_transition do |a|
      i, j = 0, 0
      while i == j
        i, j = rand(a.size), rand(a.size)
      end
      [i, j]
    end

    transition do |a, swap|
      i, j = swap.first, swap.last
      a[i], a[j] = a[j], a[i]
    end
  end
end


# Tests

def line_graph(number_of_nodes)
  nodes = (1..number_of_nodes).to_a
  edges = []
  until nodes.empty?
    i = nodes.delete_at rand(nodes.size)
    edges << [i, i+1]
  end
  return Graph.from_undirected_edges(*edges)
end

graph = line_graph(200)

result = 0
puts Benchmark.measure { result = bandwith(graph) } 
puts total_cost(graph, result)

