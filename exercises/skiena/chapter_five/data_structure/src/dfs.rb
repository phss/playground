# DFS logic
class DepthFirstSearch

  def initialize(graph, visitor_actions)
    do_nothing = lambda { |*anything| }

    @graph = graph
    @actions = { :on_node_entry => do_nothing, :on_node_exit => do_nothing,
                 :on_any_edge => do_nothing, :on_unique_edge => do_nothing }.merge(visitor_actions)
    @status = @graph.nodes.reduce({}) {|result, node| result[node] = :undiscovered; result }
    @parents, @node_times, @time = {}, {}, 0
  end

  def search(node)
    visit_on_entry node

    @graph.edges_for(node).each do |edge_node|
      visit_edge(node, edge_node)

      if undiscovered? edge_node
        visit_unique_edge(node, edge_node)    
        @parents[edge_node] = node
        search(edge_node)
      end
    end

    visit_on_exit node
  end

  def parent_of(node)
    @parents[node]
  end

  def undiscovered?(node)
    @status[node] == :undiscovered
  end

  def entered(node)
    @node_times[node][:entry]
  end

  def exited(node)
    @node_times[node][:exit]
  end

  def edge_type(from, to)
    if undiscovered? to
      return :tree
    elsif @status[to] == :discovered
      return :back
    elsif @status[to] == :processed
      return entered(from) < entered(to) ? :forward : :cross
    else
      raise "Unknown type from #{from} to #{to}!"
    end
  end

  private

  def visit_on_entry(node)
    @status[node] = :discovered
    @node_times[node] = { :entry => @time }
    @time += 1
    @actions[:on_node_entry].call(self, node)
  end

  def visit_on_exit(node)
    @status[node] = :processed    
    @node_times[node][:exit] = @time
    @time += 1
    @actions[:on_node_exit].call(self, node)
  end

  def visit_edge(from, to)
    @actions[:on_any_edge].call(self, from, to)
  end

  def visit_unique_edge(from, to)
    @actions[:on_unique_edge].call(self, from, to)
  end

end
