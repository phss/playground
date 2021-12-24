def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        return [line.split(' ') for line in fileLines]
    
def pretty(input):
    return "".join(map(str, reversed(input)))

def allSolutions(monad):
    states = [(0, { 'w': 0, 'x': 0, 'y': 0, 'z': 0 })]

    def pruneStates():
        uniqueStates = {}
        for state in states:
            model, variables = state
            hashedState = '%d-%d-%d' % (variables['x'], variables['y'], variables['z'])
            if (not hashedState in uniqueStates) or (uniqueStates[hashedState][0] < model):
                uniqueStates[hashedState] = (model, variables)

        return uniqueStates.values()

    def expandStatesAndInp(a):
        newStates = []
        for state in states:
            model, variables = state
            for digit in range(1, 10):
                newModel = model*10 + digit
                newVariables = variables.copy()
                newVariables[a] = digit
                newStates.append((newModel, newVariables))
        return newStates

    def add(variables, a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] += value

    def mul(variables, a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] *= value

    def div(variables, a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] //= value

    def mod(variables, a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] %= value
        
    def eql(variables, a, b):
        value = variables[b] if b in variables else int(b)
        variables[a] = 1 if variables[a] == value else 0

    digits = 0
    for i, instruction in enumerate(monad):
        op, rest = instruction[0], instruction[1:]
        print('>', i, op, rest)

        if op == 'inp':
            print(digits, len(states), "states (before)")
            states = pruneStates()
            print(digits, len(states), "states (after prune)")
            states = expandStatesAndInp(rest[0])
            print(digits, len(states), "states (after expansion)")
            digits += 1
            continue

        for state in states:
            _, variables = state
            if op == 'add':
                add(variables, rest[0], rest[1])
            elif op == 'mul':
                mul(variables, rest[0], rest[1])
            elif op == 'div':
                div(variables, rest[0], rest[1])
            elif op == 'mod':
                mod(variables, rest[0], rest[1])
            elif op == 'eql':
                eql(variables, rest[0], rest[1])
        
    solutions = []
    for state in states:
        model, variables = state
        if variables['z'] == 0:
            solutions.append(model)

    return solutions

def solve(input) -> int:
    solutions = allSolutions(input)
    print(solutions)
    return max(solutions)

def main():
    input = parse('data/day24.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()