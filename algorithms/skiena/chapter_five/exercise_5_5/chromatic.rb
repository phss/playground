require File.dirname(__FILE__) + "/../data_structure/src/graph"

# Chromatic number of graphs with at most degrees of 2

COLORS = [:white, :black, :red]

g1 = Graph.from_undirected_edges([1, 2], [1, 3])
g2 = Graph.from_undirected_edges([1, 2], [1, 3], [2, 3])
g3 = Graph.from_undirected_edges([1, 2], [1, 3], [2, 4], [3, 5], [4, 6], [5, 6])
g4 = Graph.from_undirected_edges([1, 2], [1, 3], [2, 4], [3, 5], [4, 5])


def chromatic(graph)
  node_colors = { 1 => :white}

  graph.bfs(1) do
    on_any_edge do |search, from, to|
      if search.undiscovered? to
        node_colors[to] = next_color_for(node_colors[from])
      else
        from_color = node_colors[from]
        to_color = node_colors[to]
        if from_color == to_color
          node_colors[to] = :red
        end
      end
    end
  end

  node_colors
end

def next_color_for(color)
  color == :white ? :black : :white
end

puts "G1: #{chromatic(g1)}" 
puts "G2: #{chromatic(g2)}" 
puts "G3: #{chromatic(g3)}" 
puts "G4: #{chromatic(g4)}" 
