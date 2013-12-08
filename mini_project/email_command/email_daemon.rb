require "mail"
require "yaml"

config = YAML.load_file('config.yaml')

username = config['username']
password = config['password']
master = config['master']
wait_seconds = 10

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


while true
  mails = Mail.find_and_delete

  mails.each do |mail|
    if mail.from.include? master
      puts "Replying to #{master} on '#{mail.subject}'"
      mail.reply do
        body "This is what you said: #{mail.text_part.body}"
      end.deliver
    end
  end

  sleep wait_seconds
end
