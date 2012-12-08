class Event
  include MongoMapper::Document

  key :title, String
  key :description, String
  key :start_at, Time
  key :end_at, Time

  key :participant_ids, Array
  many :participants, :class_name => 'User', :in => :participant_ids

  def self.participating(user)
    Event.where(:participant_ids => user.id)
  end
end