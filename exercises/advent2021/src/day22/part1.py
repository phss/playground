def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')

        def parseRange(range):
            start, end = range[2:].split('..')
            return (int(start), int(end))

        steps = []
        for line in fileLines:
            switch, rest = line.split(' ')
            x, y, z = map(parseRange, rest.split(','))
            steps.append((switch, (x, y, z)))

        return steps 

def update(cube, axis, value):
    mutableCube = list(cube)
    mutableCube[axis] = value
    return tuple(mutableCube)

def areas(cubes):
    area = 0
    for cube in cubes:
        (startX, endX), (startY, endY), (startZ, endZ) = cube
        area += (endX - startX + 1) * (endY - startY + 1) * (endZ - startZ + 1)
    return area

def intersect(a, b):
    for axis in range(3):
        if a[axis][0] > b[axis][1] or b[axis][0] > a[axis][1]:
            return False
    return True

def encompass(a, b):
    ax, ay, az = a
    bx, by, bz = b
    inX, inY, inZ = range(bx[0], bx[1]+1), range(by[0], by[1]+1), range(bz[0], bz[1]+1)
    return (ax[0] in inX and ax[1] in inX) and (ay[0] in inY and ay[1] in inY) and (az[0] in inZ and az[1] in inZ)

def add(cubes, cubeToAdd):
    newCubes = [cubeToAdd]

    for axis in range(3):
        for cube in cubes:
            newNewCubes = []
            for newCube in newCubes:
                if intersect(cube, newCube):
                    if newCube[axis][0] < cube[axis][0]:
                        newNewCubes.append(update(newCube, axis, (newCube[axis][0], cube[axis][0]-1)))
                        newCube = update(newCube, axis, (cube[axis][0], newCube[axis][1]))
                    if newCube[axis][1] > cube[axis][1]:
                        newNewCubes.append(update(newCube, axis, (cube[axis][1]+1, newCube[axis][1])))
                        newCube = update(newCube, axis, (newCube[axis][0], cube[axis][1]))
                if not encompass(newCube, cube):
                    newNewCubes.append(newCube)
            newCubes = newNewCubes

    return newCubes

def remove(cubes, cubeToRemove):
    afterRemove = []

    for cube in cubes:
        if intersect(cube, cubeToRemove):
            for axis in range(3):
                if cubeToRemove[axis][0] > cube[axis][0]:
                    afterRemove.append(update(cube, axis, (cube[axis][0], cubeToRemove[axis][0]-1)))
                    cube = update(cube, axis, (cubeToRemove[axis][0], cube[axis][1]))
                if cubeToRemove[axis][1] < cube[axis][1]:
                    afterRemove.append(update(cube, axis, (cubeToRemove[axis][1]+1, cube[axis][1])))
                    cube = update(cube, axis, (cube[axis][0], cubeToRemove[axis][1]))
        else:
            afterRemove.append(cube)

    return afterRemove

def solve(input) -> int:
    smallCubes = []
    for step in input:
        _, cube = step
        small = True
        for axis in range(3):
            if cube[axis][0] < -50 or cube[axis][0] > 50 or cube[axis][1] < -50 or cube[axis][1] > 50:
                small = False
                break
        if small:
            smallCubes.append(step)

    _, firstCube = smallCubes[0]
    cubes = [firstCube]

    for step in smallCubes[1:]:
        switch, cube = step


        if switch == 'on':
            cubes += add(cubes, cube)
        elif switch == 'off':
            cubes = remove(cubes, cube)

    return areas(cubes)

def main():
    input = parse('data/day22.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()