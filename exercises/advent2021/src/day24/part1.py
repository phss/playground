def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        return [line.split(' ') for line in fileLines]
    
def run(monad, input):
    variables = { 'w': 0, 'x': 0, 'y': 0, 'z': 0 }

    def inp(a):
        variables[a] = input.pop()

    def add(a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] += value

    def mul(a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] *= value

    def div(a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] //= value

    def mod(a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] %= value
        
    def eql(a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] = 1 if variables[a] == value else 0

    for instruction in monad:
        op, rest = instruction[0], instruction[1:]
        if op == 'inp':
            inp(rest[0])
        elif op == 'add':
            add(rest[0], rest[1])
        elif op == 'mul':
            mul(rest[0], rest[1])
        elif op == 'div':
            div(rest[0], rest[1])
        elif op == 'mod':
            mod(rest[0], rest[1])
        elif op == 'eql':
            eql(rest[0], rest[1])

    return variables['z']

def solve(monad) -> int:
    return run(monad, [5, 2])

def main():
    input = parse('data/temp.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()