
class School

  attr_reader :db

  def initialize
    @db = Hash.new { |db, grade| db[grade] = [] }
  end

  def add(student_name, grade_number)
    @db[grade_number] << student_name
  end

  def grade(grade_number)
    @db[grade_number]
  end

  def sort
    sorted_db = Hash[@db.sort]
    sorted_db.each { |grade, students| students.sort! }
    return sorted_db
  end

end

