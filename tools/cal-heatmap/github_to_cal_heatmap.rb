require "json"
require "date"

github_data = JSON.load(File.read('github_raw.json'))
data = {}

github_data.each do |d|
  date_string, value = d
  date_in_seconds = Date.parse(date_string).strftime("%s")
  data[date_in_seconds] = value unless value == 0
end

puts data.to_json
