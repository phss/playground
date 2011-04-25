require File.dirname(__FILE__) + "/../data_structure/src/graph"

# Number of shortest paths

graphs = {
  :one_edge => Graph.from_directed_edges([:v, :w]), # 1 
  :lozange => Graph.from_directed_edges([:v, 2], [:v, 3], [2, :w], [3, :w]), # 2 
  :early_split => Graph.from_directed_edges([:v, 2], [2, 3], [2, 4], [3, 5], [3, 6], [4, 6], [6, :w]), # 2 
}

def shortest(graph)
  level = {}
  distinct_paths = Hash.new(0)
  distinct_paths[:v] = 1

  graph.bfs(:v) do
    on_node_entry do |search, node|
      parent = search.parent_of(node)
      level[node] = (parent.nil? ? 0 : level[parent]) + 1
    end

    on_any_edge do |search, from, to|
      distinct_paths[to] += distinct_paths[from] if level[from] == level[search.parent_of(to)]
    end
  end

  return distinct_paths[:w]
end

graphs.each do |name, graph|
  puts "#{name}: #{shortest(graph)}"
end
