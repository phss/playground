require 'net/pop'

pop = Net::POP3.new 'pop.mailinator.com'
pop.start 'mail.test@mailinator.com', 'password'

if pop.mails.empty?
  puts "No mail."
else
  puts "You have #{pop.mails.length} new messages."

  pop.mails.each { |mail| puts mail.pop }
end