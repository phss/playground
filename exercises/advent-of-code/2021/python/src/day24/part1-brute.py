def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        return [line.split(' ') for line in fileLines]
    
def pretty(input):
    return "".join(map(str, reversed(input)))

def allSolutions(monad):
    states = [(0, [0, 0, 0, 0])]

    def pruneStates():
        uniqueStates = {}
        for state in states:
            model, variables = state
            hashedState = '%d-%d-%d' % (variables[1], variables[2], variables[3])
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
                newVariables[0] = digit
                newStates.append((newModel, newVariables))
        return newStates

    def vtoi(a):
        if a == 'w':
            return 0
        elif a == 'x':
            return 1
        elif a == 'y':
            return 2
        elif a == 'z':
            return 3
        else:
            return -1

    def add(variables, a, b):
        bi = vtoi(b)
        value = variables[bi] if bi != -1 else int(b)
        variables[vtoi(a)] += value

    def mul(variables, a, b):
        bi = vtoi(b)
        value = variables[bi] if bi != -1 else int(b)
        variables[vtoi(a)] *= value

    def div(variables, a, b):
        bi = vtoi(b)
        value = variables[bi] if bi != -1 else int(b)
        variables[vtoi(a)] //= value

    def mod(variables, a, b):
        bi = vtoi(b)
        value = variables[bi] if bi != -1 else int(b)
        variables[vtoi(a)] %= value
        
    def eql(variables, a, b):
        bi = vtoi(b)
        value = variables[bi] if bi != -1 else int(b)
        variables[vtoi(a)] = 1 if variables[vtoi(a)] == value else 0

    digits = 0
    for i, instruction in enumerate(monad):
        op, rest = instruction[0], instruction[1:]
        # print('>', i, op, rest)

        if op == 'inp':
            print(digits, len(states), "states (before)")
            states = pruneStates()
            print(digits, len(states), "states (after prune)")
            states = expandStatesAndInp(rest[0])
            print(digits, len(states), "states (after expansion)")
            digits += 1

            things = sorted(states, key=lambda x: x[0])
            print("Min", things[0])
            print("Max", things[-1])
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
        if variables[3] == 0:
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