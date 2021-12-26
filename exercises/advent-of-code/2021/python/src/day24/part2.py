def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        return [line.split(' ') for line in fileLines]
    
def pretty(input):
    return "".join(map(str, reversed(input)))

def calculate(stateVariables, stepVariables):
    w, x, y, z = stateVariables
    a, b, c = stepVariables

    x = (z % 26) + a
    z //= b
    x = 1 if x != w else 0
    y = (25 * x) + 1
    z *= y
    y = (w + c) * x
    z += y

    return [w, x, y, z]

def allSolutions(monad):
    states = [(0, [0, 0, 0, 0])]
    stepVariables = [
        [13, 1, 14],
        [12, 1, 8],
        [11, 1, 5],
        [0, 26, 4],
        [15, 1, 10],
        [-13, 26, 13],
        [10, 1, 16],
        [-9, 26, 5],
        [11, 1, 6],
        [13, 1, 13],
        [-14, 26, 6],
        [-3, 26, 7],
        [-2, 26, 13],
        [-14, 26, 3]
    ]

    def pruneStates():
        uniqueStates = {}
        for state in states:
            model, variables = state
            hashedState = '%d-%d-%d' % (variables[1], variables[2], variables[3])
            if (not hashedState in uniqueStates) or (uniqueStates[hashedState][0] > model):
                uniqueStates[hashedState] = (model, variables)

        return uniqueStates.values()

    def expandStatesAndInp():
        newStates = []
        for state in states:
            model, variables = state
            for digit in range(1, 10):
                newModel = model*10 + digit
                newVariables = variables.copy()
                newVariables[0] = digit

                if newModel < 13 or newModel > 99:
                    newStates.append((newModel, newVariables))
        return newStates

    digits = 0
    for step, stepVars in enumerate(stepVariables):
        print(">", step)
        print(digits, len(states), "states (before)")
        states = pruneStates()
        print(digits, len(states), "states (after prune)")
        states = expandStatesAndInp()
        print(digits, len(states), "states (after expansion)")
        digits += 1

        things = sorted(states, key=lambda x: x[0])
        print("Min", things[0])
        print("Max", things[-1])

        for i, state in enumerate(states):
            model, stateVars = state
            newStateVars = calculate(stateVars, stepVars)
            states[i] = (model, newStateVars)
    
    print("Done! Calculating solutions.")
        
    solutions = []
    for state in states:
        model, variables = state
        if variables[3] == 0:
            solutions.append(model)

    return solutions

def solve(input) -> int:
    solutions = allSolutions(input)
    print(solutions)
    return min(solutions)

def main():
    input = parse('data/day24.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()