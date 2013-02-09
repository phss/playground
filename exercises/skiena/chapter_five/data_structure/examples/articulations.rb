require File.dirname(__FILE__) + "/../src/graph"   

# Use DFS to number of articulation points in a graph

one_articulation = Graph.from_undirected_edges([1, 2], [1, 3], [2, 4], [3, 4], [4, 5], [4, 6], [5, 7], [5, 8], [6, 7], [7, 9], [8, 9], [8, 10], [9, 10]) # At 4
complex = Graph.from_undirected_edges([1, 2], [1, 3], [2, 3], [3, 4], [4, 5], [5, 6], [4, 6], [1, 7], [7, 8], [1, 8], [8, 9], [8, 10], [9, 10], [9, 11], [10, 11])

def articulations(graph)
  total = 0
  top_ancestor = {}
  tree_degrees = Hash.new(0)

  graph.dfs(graph.nodes.first) do 
    on_node_entry { |search, node| top_ancestor[node] = node }

    on_any_edge do |search, from, to| 
      type = search.edge_type(from, to)
      if type == :tree
        tree_degrees[from] += 1
      else if type == :back && search.entered(to) < search.entered(top_ancestor[from])
          top_ancestor[from] = to
        end
      end
    end

    on_node_exit do |search, node|
      parent = search.parent_of(node)

      if parent.nil? # root
        if tree_degrees[node] > 1
          puts "At root #{node}"
          total += 1 
        end
      else
        if top_ancestor[node] == node && tree_degrees[node] > 0
          puts "At node #{node}"
          total += 1
        end
        if top_ancestor[node] == parent && !search.parent_of(parent).nil?
          puts "At parent #{parent}"
          total += 1
        end
        
        if search.entered(top_ancestor[node]) < search.entered(top_ancestor[parent])
          top_ancestor[parent] = top_ancestor[node]
        end
      end
    end
  end

  return total
end

puts "Total: #{articulations(one_articulation)}"
puts
puts "Total: #{articulations(complex)}"
