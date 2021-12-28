def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')

        width, height = len(fileLines[0]), len(fileLines)
        eastHerd, southHerd = set(), set()
        for y, line in enumerate(fileLines):
            for x, char in enumerate(line):
                if char == '>':
                    eastHerd.add((x, y))
                elif char == 'v':
                    southHerd.add((x, y))
        return ((width, height), (eastHerd, southHerd))

def pp(seafloor):
    (width, height), (eastHerd, southHerd) = seafloor
    for y in range(height):
        for x in range(width):
            if (x, y) in eastHerd:
                char = '>'
            elif (x, y) in southHerd:
                char = 'v'
            else:
                char = '.'
            print(char, end='')
        print()
    print('------------------')

def moveSeaCucumbers(seafloor):
    (width, height), (eastHerd, southHerd) = seafloor
    eastHerdAfterMove, southHerdAfterMove = set(), set()
    moving = False

    for cucumber in eastHerd:
        x, y = cucumber
        cucumberAfterMove = ((x + 1) % width, y)
        if (not cucumberAfterMove in eastHerd) and (not cucumberAfterMove in southHerd):
            moving = True
            eastHerdAfterMove.add(cucumberAfterMove)
        else:
            eastHerdAfterMove.add(cucumber)

    for cucumber in southHerd:
        x, y = cucumber
        cucumberAfterMove = (x, (y + 1) % height)
        if (not cucumberAfterMove in eastHerdAfterMove) and (not cucumberAfterMove in southHerd):
            moving = True
            southHerdAfterMove.add(cucumberAfterMove)
        else:
            southHerdAfterMove.add(cucumber)

    return (moving, ((width, height), (eastHerdAfterMove, southHerdAfterMove)))
    
def solve(seafloor) -> int:
    steps = 0
    moving = True
    
    while moving:
        moving, seafloor = moveSeaCucumbers(seafloor)
        steps += 1

    pp(seafloor)

    return steps 

def main():
    input = parse('data/day25.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()