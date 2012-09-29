require 'mail'

Mail.defaults do
  retriever_method :pop3, :address    => 'pop.mailinator.com',
                          :port       => 110,
                          :user_name  => 'mail.test@mailinator.com',
                          :password   => 'password',
                          :enable_ssl => false
end

Mail.all.each do |mail|
  puts "From: #{mail.from}"
  puts "Subject: #{mail.subject}"
  puts mail.text_part.body
  puts "===\n\n"
end
