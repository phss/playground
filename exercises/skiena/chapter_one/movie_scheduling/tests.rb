require "test/unit"

require "optimal_scheduling"

class TestAlgorithms < Test::Unit::TestCase
  
  def setup
    @algo_movies = [
      Movie.new("The President's Algorist", 0, 5),
      Movie.new("'Discrete' Mathematics", 1, 3),
      Movie.new("Tarjan of the Jungle", 2, 7),
      Movie.new("Halting State", 4, 8),
      Movie.new("Steiner's Tree", 6, 10),
      Movie.new("The Four Volume Problem", 9, 15),
      Movie.new("Programming Challenges", 11, 13),
      Movie.new("Process Terminated", 12, 16),
      Movie.new("Calculated Bets", 14, 17)
    ]
   @shorts = [
     Movie.new("Long", 0, 11),
     Movie.new("Short A", 1, 2),
     Movie.new("Short B", 3, 4),
     Movie.new("Short C", 5, 6),
     Movie.new("Short D", 7, 8),
     Movie.new("Short E", 9, 10)
   ]
   @longs = [
     Movie.new("Long A", 0, 5),
     Movie.new("Short", 4, 7),
     Movie.new("Long B", 6, 12)
   ]
  end
  
  def test_optimal_scheduling
    assert_expected_movies(@algo_movies, 4)
    assert_expected_movies(@shorts, 5)
    assert_expected_movies(@longs, 2)
  end
  
  private
  
  def assert_expected_movies(movies, total_expected)
    result = OptimalScheduling.schedule_movies(movies)
    puts "\nResults:"
    result.each do |movie|
      puts "- #{movie.name}"
    end
    assert_equal(total_expected, result.size)
  end
  
end
