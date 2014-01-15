
class Hamming

  def self.compute(strand_a, strand_b)
    strands = zip_strands(strand_a, strand_b)    
    strands.count do |nucleotide_a, nucleotide_b|
      nucleotide_a != nucleotide_b
    end
  end

 private

  def self.zip_strands(a, b)
    a, b = b, a if a.size > b.size
    a.chars.zip(b.chars)
  end

end
