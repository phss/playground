
class Hamming

  def self.compute(strand_a, strand_b)
    strands = join_strands(strand_a, strand_b)    
    mutations = strands.select do |nucleotide_a, nucleotide_b|
      nucleotide_a != nucleotide_b
    end
    mutations.size
  end

 private

  def self.join_strands(a, b)
    a, b = b, a if a.size > b.size
    a.chars.zip(b.chars)
  end

end
