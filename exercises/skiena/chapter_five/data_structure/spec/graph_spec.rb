require File.dirname(__FILE__) + "/../src/graph"   

describe Graph do
  SIMPLE_GRAPH_EDGES = [[1, 2], [2, 4], [4, 3], [1, 3]]

  describe "(construction)" do
    it "should construct undirected graph" do
      graph = Graph.from_undirected_edges(*SIMPLE_GRAPH_EDGES)
      graph.nodes.should =~ [1, 2, 3, 4]
      graph.edges_for(1).should =~ [2, 3]
      graph.edges_for(2).should =~ [1, 4]
      graph.edges_for(3).should =~ [1, 4]
      graph.edges_for(4).should =~ [2, 3]
    end

    it "should construct directed graph" do
      graph = Graph.from_directed_edges(*SIMPLE_GRAPH_EDGES)
      graph.nodes.should =~ [1, 2, 3, 4]
      graph.edges_for(1).should =~ [2, 3]
      graph.edges_for(2).should =~ [4]
      graph.edges_for(3).should =~ []
      graph.edges_for(4).should =~ [3]
    end

  end

  describe "(bfs traversal)" do
    it "should traverse connected undirected graph in bfs order" do
      graph = Graph.from_undirected_edges(*SIMPLE_GRAPH_EDGES)
      entered_nodes, exited_nodes, all_processed_edges, unique_processed_edges = [], [], [], []

      graph.bfs(1) do
        on_node_entry { |graph, node| entered_nodes << node  }
        on_node_exit { |graph, node| exited_nodes << node  }
        on_any_edge { |graph, from, to| all_processed_edges << [from, to]  }
        on_unique_edge { |graph, from, to| unique_processed_edges << [from, to]  }
      end

      entered_nodes.should == [1, 2, 3, 4]
      exited_nodes.should == [1, 2, 3, 4]
      all_processed_edges.should == [[1, 2], [1, 3], [2, 1], [2, 4], [3, 1], [3, 4], [4, 2], [4, 3]]
      unique_processed_edges.should == [[1, 2], [1, 3], [2, 4]]
    end

    it "should traverse connected directed graph in bfs order" do
      graph = Graph.from_directed_edges(*SIMPLE_GRAPH_EDGES)
      entered_nodes, all_processed_edges, unique_processed_edges = [], [], []

      graph.bfs(1) do
        on_node_entry { |graph, node| entered_nodes << node  }
        on_any_edge { |graph, from, to| all_processed_edges << [from, to]  }
        on_unique_edge { |graph, from, to| unique_processed_edges << [from, to]  }
      end

      entered_nodes.should == [1, 2, 3, 4]
      all_processed_edges.should == [[1, 2], [1, 3], [2, 4], [4, 3]]
      unique_processed_edges.should == [[1, 2], [1, 3], [2, 4]]
    end

    it "should mark parent nodes" do
      search = Graph.from_undirected_edges(*SIMPLE_GRAPH_EDGES).bfs(1)
      
      search.parent_of(1).should be_nil
      search.parent_of(2).should == 1
      search.parent_of(3).should == 1
      search.parent_of(4).should == 2
    end
  end

  describe "(dfs traversal)" do 
    it "should traverse connected undirected graph in dfs order" do
      graph = Graph.from_undirected_edges(*SIMPLE_GRAPH_EDGES)
      entered_nodes, exited_nodes, all_processed_edges, unique_processed_edges = [], [], [], []

      graph.dfs(1) do
        on_node_entry { |graph, node| entered_nodes << node  }
        on_node_exit { |graph, node| exited_nodes << node  }
        on_any_edge { |graph, from, to| all_processed_edges << [from, to]  }
        on_unique_edge { |graph, from, to| unique_processed_edges << [from, to]  }
      end

      entered_nodes.should == [1, 2, 4, 3]
      exited_nodes.should == [3, 4, 2, 1]
      all_processed_edges.should == [[1, 2], [2, 1], [2, 4], [4, 2], [4, 3], [3, 1], [3, 4], [1, 3]]
      unique_processed_edges.should == [[1, 2], [2, 4], [4, 3]]
    end

    it "should traverse connected directed graph in dfs order" do
      graph = Graph.from_directed_edges(*SIMPLE_GRAPH_EDGES)
      entered_nodes, exited_nodes, all_processed_edges, unique_processed_edges = [], [], [], []

      graph.dfs(1) do
        on_node_entry { |graph, node| entered_nodes << node  }
        on_node_exit { |graph, node| exited_nodes << node  }
        on_any_edge { |graph, from, to| all_processed_edges << [from, to]  }
        on_unique_edge { |graph, from, to| unique_processed_edges << [from, to]  }
      end

      entered_nodes.should == [1, 2, 4, 3]
      exited_nodes.should == [3, 4, 2, 1]
      all_processed_edges.should == [[1, 2], [2, 4], [4, 3], [1, 3]]
      unique_processed_edges.should == [[1, 2], [2, 4], [4, 3]]
    end

  end

end
