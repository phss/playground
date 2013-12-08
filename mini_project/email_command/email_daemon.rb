require "mail"
require "yaml"

config = YAML.load_file('config.yaml')

username = config['username']
password = config['password']
master = config['master']

Mail.defaults do
  retriever_method :imap, :address => "imap.gmail.com",
                          :port => 993,
                          :enable_ssl => true,
                          :user_name => username,
                          :password => password
  delivery_method :smtp, :address => "smtp.gmail.com", 
                         :port => 587,
                         :enable_starttls_auto => true,
                         :authentication => 'plain',
                         :user_name => username,
                         :password => password
end


#mails = Mail.find_and_delete
mails = Mail.all

mails.each do |mail|
  if mail.from.include? master
    puts "Replying to #{master}"
    mail.reply do
      body "This is what you said: #{mail.text_part.body}"
    end.deliver
  end
end

