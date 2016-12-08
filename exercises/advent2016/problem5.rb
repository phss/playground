require 'digest'

def md5(str)
  Digest::MD5.hexdigest(str)
end

#puts md5("abc3231929").start_with?("00000")

door_id = "reyedfim"
i = 0
result = Array.new(8)
while result.compact.size < 8
  s = md5("#{door_id}#{i}")
  if s.start_with?("00000")
    result_index = s[5].to_i
    if s[5] =~ /[0-7]/ && result[result_index].nil?
      result[result_index] = s[6]
    end
  end
  i += 1
end

puts result.join
