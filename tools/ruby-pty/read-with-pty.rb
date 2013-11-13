require 'pty'

master, slave = PTY.open
read, write = IO.pipe
pid = spawn("./sample-script", :in=>read, :out=>slave)
read.close
slave.close

puts master.gets
puts master.gets
