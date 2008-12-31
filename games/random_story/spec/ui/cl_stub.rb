module Kernel
  alias_method :old_puts, :puts
  alias_method :old_gets, :gets
end

@@output = []
@@input = []

def puts(s)
  @@output << s.to_s
end

def gets
  @@input.shift
end