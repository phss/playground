require 'pty'

def read_from(stream)
  begin
    stream.read_nonblock(1000).split("\n").map(&:chomp)
  rescue
    nil
  end
end

master, slave = PTY.open
read, write = IO.pipe
pid = spawn("./sample-script", :in=>read, :out=>slave)

puts "PID: #{pid}"

text = nil
while text.nil?
  text = read_from(master)
end

puts "Script said: '#{text}'"

