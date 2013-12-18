
class School

  attr_reader :db

  def initialize
    @db = {}
  end

  def add(student_name, grade)
    @db[grade] ||= []
    @db[grade] << student_name
  end

end

