import common

def solve(input: list[common.Line], size: int) -> int:
    board = [ [0]*size for i in range(size)]

    for line in input:
        dirX, dirY, length = 0, 0, 0

        if line.a.x < line.b.x:
            dirX = 1
            length = line.b.x - line.a.x
        elif line.a.x > line.b.x:
            dirX = -1
            length = line.a.x - line.b.x

        if line.a.y < line.b.y:
            dirY = 1
            length = line.b.y - line.a.y
        elif line.a.y > line.b.y:
            dirY = -1
            length = line.a.y - line.b.y

        for i in range(length + 1):
            board[line.a.y + (i*dirY)][line.a.x + (i*dirX)] += 1


    dangerPoints = 0        
    for y in range(size):
        for x in range(size):
            if board[y][x] > 1:
                dangerPoints += 1

    return dangerPoints

def main():
    input = common.parse('data/day5.txt')
    result = solve(input, 1000)
    print(result)

if __name__ == '__main__':
    main()