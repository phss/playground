require "mail"
require "yaml"

config = YAML.load_file('config.yaml')

username = config['username']
password = config['password']
master = config['master']
wait_seconds = 10

commands = {
  'list files' => 'ls -ltr',
  'time?' => 'date'
}

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

      message = mail.text_part.body.decoded
      reply_body = "This is what you said: #{message}\n\n"

      commands.each do |pattern, command|
        if message.include? pattern
          result = %x(#{command})

          puts "- Responding to '#{pattern}' with '#{result}'"

          reply_body += "#{result}\n\n"
        end
      end

      mail.reply do
        body reply_body
      end.deliver
    end
  end

  sleep wait_seconds
end
