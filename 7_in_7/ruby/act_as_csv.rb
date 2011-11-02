module ActsAsCsv
  def self.included(base)
    base.extend ClassMethods
  end
  
  module ClassMethods
    def acts_as_csv
      include InstanceMethods
    end
  end
  
  module InstanceMethods   
    def read
      @csv_contents = []
      filename = self.class.to_s.downcase + '.txt'
      file = File.new(filename)
      @headers = file.gets.chomp.split(', ')

      file.each do |row|
        @csv_contents << row.chomp.split(', ')
      end
    end

    def each
      @csv_contents.each { |content| yield CsvRow.new(@headers, content) }
    end
    
    attr_accessor :headers, :csv_contents
    def initialize
      read 
    end
  end
end

class CsvRow
  def initialize(headers, contents)
    @headers, @contents = headers, contents
  end

  def method_missing(meth, *args, &blk)
    header = meth.to_s
    raise "No header #{header}" unless @headers.include? header

    return @contents[@headers.index(header)]
  end
end

class RubyCsv  # no inheritance! You can mix it in
  include ActsAsCsv
  acts_as_csv
end

m = RubyCsv.new
puts m.headers.inspect
puts m.csv_contents.inspect

m.each { |row| puts row.b }