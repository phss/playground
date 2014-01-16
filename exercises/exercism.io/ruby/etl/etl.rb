class ETL

  def self.transform(old_data)
    old_data.each_with_object({}) do |(value, letters), new_data|
      letters.each do |letter|
        new_data[letter.downcase] = value
      end
    end
  end

end
