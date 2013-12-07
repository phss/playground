require "mail"

Mail.defaults do
  retriever_method :imap, :address => "imap.gmail.com",
                          :port => 993,
                          :enable_ssl => true,
                          :user_name => ARGV.shift,
                          :password => ARGV.shift
end

puts Mail.first
