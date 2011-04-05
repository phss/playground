require "rubygems"
require "sequel"
require "rubygems"

DB = Sequel.connect("mysql://localhost:3306/information_schema", :user => "user", :password => "pass")

def dependent_tables(table_name, parent = nil)
#  puts table_name
  node = DBNode.new(table_name, parent)
  parent.add(node) if parent

  dependents = DB[:REFERENTIAL_CONSTRAINTS].filter(:REFERENCED_TABLE_NAME=>table_name).select_map(:TABLE_NAME).uniq
  dependents.each do |dependent|
    dependent_tables(dependent, node)
  end

  return node
end

class DBNode
  attr_reader :table_name

  def initialize(table_name, parent=null)
    @table_name = table_name
    @parent = parent
    @children = []
  end

  def add(child)
    @children << child
  end

  def reference_column
    DB[:KEY_COLUMN_USAGE].filter(:REFERENCED_TABLE_NAME => @parent.table_name,
                                 :TABLE_NAME => table_name).select_map(:COLUMN_NAME)
  end

  def primary_key_column
    DB[:KEY_COLUMN_USAGE].filter(:TABLE_NAME => @table_name, :CONSTRAINT_NAME => "PRIMARY").select_map(:COLUMN_NAME)
  end

  def delete_order
    @children.each { |child| child.delete_order }
    puts @table_name
  end

  def select_query(id, recurse=false)
    @children.each { |child| puts child.select_query(id, true) } if recurse
    if @parent.nil?
      "SELECT #{primary_key_column} FROM #{@table_name} WHERE #{primary_key_column} = '#{id}'"
    else
      "SELECT #{primary_key_column} FROM #{@table_name} WHERE #{reference_column} IN (#{@parent.select_query(id)})"
    end
  end

  def delete_query(id)
    @children.each { |child| child.delete_query(id) } 
    if @parent.nil?
      puts "DELETE FROM #{@table_name} WHERE #{primary_key_column} = '#{id}';\n\n"
    else
      puts "DELETE FROM #{@table_name} WHERE #{reference_column} IN (#{@parent.select_query(id)});\n\n"
    end
  end
end



root = dependent_tables(ARGV[0])
root.delete_query("YOUR_ID")
