
def next_n(n)
  if n.even?
    n / 2
  else
    3 * n + 1
  end
end

def longest_collatz_under(n)
  collatz_lengths = {1 => 1}

  1.upto(n-1) do |i|
    x, seq = i, []

    seq << x and x = next_n(x) while !(collatz_lengths.has_key? x)

    base = collatz_lengths[x]

    seq.reverse.each_with_index do |elem, idx|
      collatz_lengths[elem] = base + idx + 1
    end
  end

  collatz_lengths.sort_by { |n, length| length }.last
end


puts longest_collatz_under(1000000)

