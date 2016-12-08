require 'digest'

def md5(str)
  Digest::MD5.hexdigest(str)
end

#puts md5("abc3231929").start_with?("00000")

i = 0
result = []
while result.size < 8
  s = md5("reyedfim#{i}")
  if s.start_with?("00000")
    result << s[5]
  end
  i += 1
end

puts result.join
