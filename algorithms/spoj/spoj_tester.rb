module Kernel
  alias_method :old_puts, :puts
  alias_method :old_gets, :gets
end

@@input  = []
@@output = []
@@expected_output = []

def puts(s)
  @@output << s.to_s
end

def gets
  @@input.shift
end


def run_solution(solution_file, test_files)
  old_puts "Running #{solution_file}:"
  test_files.each do |test_file|
    load_test_file(test_file)
    load(solution_file)
    if @@output == @@expected_output
      old_puts "- [PASSED] #{test_file}"
    else
      old_puts "- [FAILED] #{test_file}"
      old_puts "    Expected: #{@@expected_output.inspect} / Actual: #{@@output.inspect}"
    end
  end
end

def load_test_file(test_file)
  @@output = []
  @@input = []
  @@expected_output = []
  reading_input = true
  File.open(test_file) do |file|
    while (line = file.gets)
      break if line.chomp == "==="
      @@input << line
    end
    @@expected_output << line.chomp while (line = file.gets)
  end
end

if __FILE__ == $PROGRAM_NAME
  exit if ARGV.size < 2
  run_solution(ARGV[0], ARGV[1..-1])
end