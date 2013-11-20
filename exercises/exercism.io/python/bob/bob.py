class Bob(object):
    
    def hey(self, phrase):
        if not phrase.strip():
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
