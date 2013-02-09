# BFS logic
class BreadthFirstSearch

  def initialize(graph, visitor_actions)
    do_nothing = lambda { |*anything| }

    @graph = graph
    @actions = { :on_node_entry => do_nothing, :on_node_exit => do_nothing,
                 :on_any_edge => do_nothing, :on_unique_edge => do_nothing }.merge(visitor_actions)
    @status = @graph.nodes.reduce({}) {|result, node| result[node] = :undiscovered; result }
    @parents = {}
  end

  def search(start_node)
    to_process = [discover(start_node, nil)]
    
    while !to_process.empty?
      current_node = visit_next_node to_process

      @graph.edges_for(current_node).each do |edge_node|
        if undiscovered? edge_node
          to_process << discover(edge_node, current_node)
          visit_unique_edge(current_node, edge_node)
        end
        visit_edge(current_node, edge_node)
      end

      visit_exit_node current_node
    end
  end

  def parent_of(node)
    @parents[node]
  end

  def undiscovered?(node)
    @status[node] == :undiscovered
  end
  
  def unprocessed?(node)
    @status[node] != :processed
  end

  def discover(node, parent)
    @status[node] = :discovered
    @parents[node] = parent
    return node
  end

  private

  def visit_next_node(queue)
    next_node = queue.shift
    @actions[:on_node_entry].call(self, next_node)
    return next_node
  end

  def visit_exit_node(node)
    @status[node] = :processed
    @actions[:on_node_exit].call(self, node)
  end

  def visit_edge(from, to)
    @actions[:on_any_edge].call(self, from, to)
  end

  def visit_unique_edge(from, to)
    @actions[:on_unique_edge].call(self, from, to)
  end

end
