DICT = ["cat", "cats", "sand", "and", "dog", "dogs", "s"]

def phrases_from(string)
  return [[]] if string.empty?

  phrases = []
  string.size.times do |index|
    substring, rest = string[0..index], string[index+1..string.size] 
    
    phrases_from(rest).map { |rest_phrase| phrases << [substring] + rest_phrase } if (DICT.include? substring)
  end

  return phrases
end


puts phrases_from("catsanddogs").to_s