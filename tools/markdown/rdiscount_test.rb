require "rubygems"
require "rdiscount"

doc = <<-EOS
Markdown test using RDiscount
===

Testing code block

    puts 'blah'
    [1, 2, 3].each do |i|
      puts i + 1
    end
    
Other stuff

Images
------

![Some alt][link]

[link]: http://blah.com/image.jpg

EOS

markdown = RDiscount.new(doc)
puts markdown.to_html