require 'pty'

def read_from(stream)
  begin
    stream.read_nonblock(1000).split("\n").map(&:chomp)
  rescue
    retry
  end
end

master, slave = PTY.open
read, write = IO.pipe
pid = spawn("./sample-script", :in=>read, :out=>slave)

puts "PID: #{pid}"

puts "Script said: '#{read_from(master)}'"

write.puts "42"

puts "Script said again: '#{read_from(master)}'"
