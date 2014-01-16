
class Hamming

  def self.compute(strand_a, strand_b)
    strands = zip_strands(strand_a, strand_b)    
    strands.count do |nucleotide_a, nucleotide_b|
      nucleotide_a != nucleotide_b
    end
  end

 private

  def self.zip_strands(a, b)
    [a, b].map(&:chars).sort_by(&:size).reduce(&:zip)
  end

end
