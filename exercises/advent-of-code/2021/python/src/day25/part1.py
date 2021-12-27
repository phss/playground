def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        return list(map(list, fileLines))

def pp(seafloor):
    for row in seafloor:
        print("".join(row))
    print('------------------')

def moveSeaCucumbers(seafloor):
    moving = False

    for y, row in enumerate(seafloor):
        x = 0
        while x < len(row):
            nextX = (x + 1) % len(row)
            if seafloor[y][x] == '>' and seafloor[y][nextX] == '.':
                moving = True
                seafloor[y][nextX] = '>'
                seafloor[y][x] = '.'
                x += 1
            x += 1

    for x in range(len(seafloor[0])):
        y = 0 
        while y < len(seafloor):
            nextY = (y + 1) % len(seafloor)
            if seafloor[y][x] == 'v' and seafloor[nextY][x] == '.':
                moving = True
                seafloor[nextY][x] = 'v'
                seafloor[y][x] = '.'
                y += 1
            y += 1

    return (moving, seafloor)
    
def solve(seafloor) -> int:
    steps = 0
    # moving = True
    # while moving:
    #     moving, seafloor = moveSeaCucumbers(seafloor)

    pp(seafloor)
    _, seafloor = moveSeaCucumbers(seafloor)
    pp(seafloor)

    return steps 

def main():
    input = parse('data/temp.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()