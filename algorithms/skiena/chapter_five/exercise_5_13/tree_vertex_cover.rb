require File.dirname(__FILE__) + "/../data_structure/src/graph"

# Vertex cover for trees

tree = Graph.from_undirected_edges([1, 2], [1, 3], [2, 4], [2, 5], [3, 6], [3, 7], [4, 8], [4, 9], [5, 10], [5, 11], [6, 12], [6, 13], [7, 14], [7, 15], [8, 16], [8, 17], [9, 18], [9, 19], [10, 20], [10, 21], [11, 22], [13, 23])

cover = []

tree.dfs(1) do 
  on_node_exit do |search, node|
    children = tree.edges_for(node) - [search.parent_of(node)]
    next if children.empty? # Leaf node

    all_included = children.reduce(true) { |included, child| included && cover.include?(child) }
    cover << node unless all_included
  end
end


puts cover.join(" ")
