require "mail"

Mail.defaults do
  retriever_method :imap, :address => "imap.gmail.com",
                          :port => 993,
                          :enable_ssl => true,
                          :user_name => ARGV.shift,
                          :password => ARGV.shift
end

mails = Mail.find_and_delete

mails.each do |mail|
  puts mail.text_part.body
end
