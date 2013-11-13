require 'pty'

def read_from(stream)
  output = []
  count = 0
  begin
    while true
      output += stream.read_nonblock(100000).split("\n").map(&:chomp)
      count = 0
    end
  rescue IO::WaitReadable => e
    count += 1
    retry if output.empty? || count < 1000
  end
  return output
end

master, slave = PTY.open
read, write = IO.pipe
pid = spawn("./sample-script", :in=>read, :out=>slave)

puts "PID: #{pid}"

puts "Script said: '#{read_from(master)}'"

write.puts "42"

puts "Script said again: '#{read_from(master)}'"
