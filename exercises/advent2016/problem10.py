import re

def parse(file):
  with open(file, 'r') as f:
    instructions = f.readlines()

  bot_instructions = []
  value_instructions = []
  for instruction in instructions:
    simpler_instruction = re.findall(r"\w+ \d+", instruction)
    if instruction.startswith('bot'):
      bot_instructions.append(simpler_instruction)
    else:
      value_instructions.append(simpler_instruction)

  return bot_instructions, value_instructions


class Bot:
  def __init__(self, name, low_ref, high_ref):
    self.name = name
    self.low_ref = low_ref
    self.high_ref = high_ref
    self.values = []

  def lower_value(self):
    return min(self.values)

  def higher_value(self):
    return max(self.values)

  def push_value(self, value):
    self.values.append(value)
    if len(self.values) > 2:
      raise "Too many values"

  def is_full(self):
    return len(self.values) == 2

  def clear(self):
    self.values = []


class Output:
  def __init__(self, name):
    self.name = name
    self.values = []

  def push_value(self, value):
    self.values.append(value)


bot_instructions, value_instructions = parse('files/problem10.txt')

bots = {}
outputs = {}

def next_full():
  for bot_ref, bot in bots.iteritems():
    if bot.is_full():
      return bot
  return None

def find_ref(ref):
  if ref in bots:
    return bots[ref]
  else:
    return outputs[ref]

for instruction in bot_instructions:
  name, low_ref, high_ref = instruction
  bots[name] = Bot(name, low_ref, high_ref)
  if low_ref.startswith('output'):
    outputs[low_ref] = Output(low_ref)
  if high_ref.startswith('output'):
    outputs[high_ref] = Output(high_ref)

for instruction in value_instructions:
  value_str, bot_ref = instruction
  value = int(value_str.split(' ')[1])
  bots[bot_ref].push_value(value)

bot = next_full()
while bot:
  if bot.lower_value() == 17 and bot.higher_value() == 61:
    print bot.name

  find_ref(bot.low_ref).push_value(bot.lower_value())
  find_ref(bot.high_ref).push_value(bot.higher_value())
  bot.clear()
  bot = next_full()

