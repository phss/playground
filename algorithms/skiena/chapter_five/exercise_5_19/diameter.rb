require File.dirname(__FILE__) + "/../data_structure/src/graph"

# Calculate tree diameter

trees = {
  :one_edge => Graph.from_undirected_edges([1, 2]), # 1
  :two_edges => Graph.from_undirected_edges([1, 2], [1, 3]), # 2
  :tricky => Graph.from_undirected_edges([1, 2], [2, 3], [2, 4]), # 2
  :small => Graph.from_undirected_edges([1, 2], [2, 3], [2, 4], [1, 5]) # 3
}

def diameter(tree)
  distance_from_leaf = {}
  max_distance = 0

  tree.dfs(1) do
    on_node_exit do |search, node|
        children = tree.edges_for(node) - [search.parent_of(node)]
        if children.empty?
          distance_from_leaf[node] = 0        
        else
          distances = children.map { |child| distance_from_leaf[child] }.sort.reverse
          max = distances.shift + 1
          second_max = distances.empty? ? 0 : distances.shift + 1
          node_max_distance = max + second_max
          
          distance_from_leaf[node] = max
          max_distance = node_max_distance if node_max_distance > max_distance
        end
    end
  end

  return max_distance
end

trees.each do |name, tree|
  puts "#{name}: #{diameter(tree)}"
end
