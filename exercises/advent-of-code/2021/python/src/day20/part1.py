
def parse(filename: str):
    with open(filename) as file:
        def toBin(elem):
            if elem == '#':
                return 1
            else:
                return 0
                
        fileLines = file.read().split('\n')
        algo = list(map(toBin, fileLines[0]))
        image = [list(map(toBin, line)) for line in fileLines[2:]]
        return (algo, image)

def pp(image):
    conversion = {0: '.', 1: '#'}
    for line in image:
        for elem in line:
            print(conversion[elem], end='')
        print()

def process(algo, image, defaultValue):
    def pixelAt(x, y):
        if x < 0 or x >= len(image[0]) or y < 0 or y >= len(image):
            return defaultValue
        else:
            return image[y][x]

    newImage = []
    for y in range(-2, len(image[0]) + 1):
        newRow = []
        for x in range(-2, len(image) + 1):
            index = 0
            for loc in [(-1, -1), (0, -1), (1, -1), (-1, 0), (0, 0), (1, 0), (-1, 1), (0, 1), (1, 1)]:
                index = index << 1
                val = pixelAt(x + loc[0], y + loc[1])
                index += val
            newRow.append(algo[index])
        newImage.append(newRow)
    return newImage

def solve(input) -> int:
    algo, image = input
    pp(image)
    newImage = process(algo, process(algo, image, 0), 1)
    pp(newImage)
    return sum(map(sum, newImage))

def main():
    input = parse("data/day20.txt")
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()