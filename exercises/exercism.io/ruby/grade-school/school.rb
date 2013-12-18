
class School

  attr_reader :db

  def initialize
    @db = {}
  end

  def add(student_name, grade_number)
    @db[grade_number] ||= []
    @db[grade_number] << student_name
  end

  def grade(grade_number)
    @db[grade_number]
  end

end

