require 'rubygems'
require 'treemap'

root = Treemap::Node.new
root.new_child(:size => 6)
root.new_child(:size => 6)
root.new_child(:size => 4)
root.new_child(:size => 3)
root.new_child(:size => 2)
root.new_child(:size => 2)
root.new_child(:size => 1)
node = Treemap::Node.new(:size => 10, :label => "Childs?")
node.new_child(:size => 4, :label => "sub1")
node.new_child(:size => 2, :label => "sub2")
node.new_child(:size => 3, :label => "sub3")
root.add_child(node)

output = Treemap::HtmlOutput.new do |o|
  o.width = 800
  o.height = 600
  o.center_labels_at_depth = 1
end

puts output.to_html(root)
