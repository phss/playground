
class Hamming

  def self.compute(strand_a, strand_b)
    strands = strand_a.split('').zip(strand_b.split(''))
    mutations = strands.select do |nucleotide_a, nucleotide_b|
      nucleotide_a != nucleotide_b
    end
    mutations.size
  end

end
