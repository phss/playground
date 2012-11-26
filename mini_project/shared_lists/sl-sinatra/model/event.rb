class Event
  attr_reader :title, :description, :start_at, :end_at, :participants

  def initialize(title, description, start_at, end_at, participants)
    @title = title
    @description = description
    @start_at = start_at
    @end_at = end_at
    @participants = participants
  end

end
