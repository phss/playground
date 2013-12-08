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

mails = Mail.find_and_delete

mails.each do |mail|
  puts mail.text_part.body
end

Mail.deliver do
  to master
  from username
  subject 'Testing'
  body 'This stuff'
end
