# 
# Algorithm:
#   While (I != 0) do
#     Accept the job j from I with the earliest completion date.
#     Delete j, and any interval which intersects j from I.
#
class OptimalScheduling

  def self.schedule_movies(movies)
    scheduled = []
    
    while movies.size > 0
      earliest_movie = movies.min { |a_movie, another_movie| a_movie.end <=> another_movie.end }
      scheduled << earliest_movie
      movies.reject! { |movie| movie.intersect(earliest_movie) }
    end
    return scheduled
  end
  
end

Movie = Struct.new(:name, :start, :end) do
  
  def intersect(another_movie)
    (self.start >= another_movie.start && self.start <= another_movie.end) ||
    (another_movie.start >= self.start && another_movie.start <= self.end)
  end
  
end