require_relative 'model/user'
require_relative 'model/event'

EVENTS = []

def bootstrap_model
  User.remove
  Event.remove

  unless User.find_by_name('Paulo')
    User.create(:name => 'Paulo')

    Event.create(:title => 'Play football',
                 :start_at => DateTime.new(2012, 11, 12, 17, 30),
                 :participants => [User.first])

    Event.create(:title => 'Boiler repairment visit',
                 :start_at => DateTime.new(2012, 11, 14, 9, 30),
                 :end_at => DateTime.new(2012, 11, 14, 13, 0),
                 :participants => [User.first])

    Event.create(:title => 'Photography course and jewelry course',
                 :description => 'Hopefully we will have a lunch break',
                 :start_at => DateTime.new(2012, 11, 17, 10, 0),
                 :end_at => DateTime.new(2012, 11, 17, 16, 0),
                 :participants => [User.first])
  end
end
