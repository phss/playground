require_relative 'model/user'
require_relative 'model/event'

USERS = [User.new('Paulo')]

EVENTS = [
  Event.new('Play football',
            '',
            DateTime.new(2012, 11, 12, 17, 30),
            nil,
            USERS[0]),

  Event.new('Boiler repairment visit',
            '',
            DateTime.new(2012, 11, 14, 9, 30),
            DateTime.new(2012, 11, 14, 13, 0),
            USERS[0]),

  Event.new('Photography course and jewelry course',
            'Hopefully we will have a lunch break',
            DateTime.new(2012, 11, 17, 10, 0),
            DateTime.new(2012, 11, 17, 16, 0),
            USERS[0] )
]
