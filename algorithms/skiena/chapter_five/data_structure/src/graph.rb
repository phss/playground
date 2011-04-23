require File.dirname(__FILE__) + "/bfs"   
require File.dirname(__FILE__) + "/dfs"   


# Graph implementation with BFS and DFS traversal
class Graph

  def initialize
    @node_to_edges = {}
  end

  #
  # Graph construction
  #

  public

  def self.from_undirected_edges(*edges)
    graph = Graph.new
    edges.each { |edge| graph << edge; graph << edge.reverse }
    graph
  end

  def self.from_directed_edges(*edges)
    graph = Graph.new
    edges.each { |edge| graph << edge }
    graph
  end

  def <<(edge)
    initialize_nodes(edge)
    @node_to_edges[edge.first] << edge.last
  end

  def nodes
    @node_to_edges.keys
  end

  def edges_for(node)
    @node_to_edges[node].sort # Doesn't really matter, but it's just to keep consistency
  end

  private 

  def initialize_nodes(nodes_to_insert)
    nodes_to_insert.each { |node| @node_to_edges[node] = [] unless nodes.include? node }
  end

  # 
  # Graph traversal
  #
  public

  def bfs(start_node, &visitor)
    return bfs_on_all([start_node], &visitor)
  end
  
  def bfs_on_all(nodes, &visitor)
    search_on_all(BreadthFirstSearch, nodes, &visitor)
  end
 
  def dfs(start_node, &visitor)
    return dfs_on_all([start_node], &visitor)
  end

  def dfs_on_all(nodes, &visitor)
    search_on_all(DepthFirstSearch, nodes, &visitor)
  end

  private
 
  def search_on_all(searcher_class, nodes, &visitor)
    searcher = searcher_class.new(self, GraphSearchVisitorParser.parse(&visitor))
    nodes.each { |node| searcher.search(node) if searcher.undiscovered? node }
    return searcher
  end

end

# Transform DSL into hash
class GraphSearchVisitorParser
  attr_reader :actions

  def initialize
    @actions = {}
  end

  def self.parse(&visitor_block)
    parser = GraphSearchVisitorParser.new
    parser.instance_eval &visitor_block unless visitor_block.nil?
    return parser.actions
  end

  def method_missing(m, *args, &block)
    @actions[m] = block
  end
end

