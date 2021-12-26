from typing import Tuple

input = ((211, 232), (-124, -69))
# input = ((20, 30), (-10, -5))
horizontal, vertical = input
startX, endX = horizontal
endY, startY = vertical

def inside(position: Tuple[int, int]) -> bool:
    x, y = position
    return x >= startX and x <= endX and y <= startY and y >= endY

def hits(initalVelocity: Tuple[int, int]) -> bool:
    velX, velY = initalVelocity
    x, y = (0, 0)
    while x <= endX and y >= endY:
        # print((x, y))
        if inside((x, y)):
            # print((x, y))
            return True
        x += velX  
        y += velY
        if velX != 0:
            velX -= 1
        velY -= 1
    return False

def calcVelX() -> list[int]:
    valid = []
    for velocity in range(endX + 1):
        velX = velocity
        x = 0
        while x <= endX:
            if x >= startX:
                valid.append(velocity)
                break
            elif velX == 0:
                break
            x += velX
            velX -= 1

    return valid

def solve() -> int:
    count = 0
    for velX in calcVelX():
        for velY in range(endY, 1000):
           if hits((velX, velY)) :
               count += 1
    return count

    # (30, -6)
    # print(hits((30, -6)))
    # print(hits((15, -3)))
    # print(hits((11, -1)))

    # print(hits((7,5)))
    # print(hits((11, -3)))
    # print(hits((11, -4)))
    # print(hits((7, 2)))
    # print(hits((6, 3)))
    # print(hits((9, 0)))
    # print(hits((17, -4)))
    # print(hits((6, 9)))
def main():
    result = solve()
    print(result)

if __name__ == '__main__':
    main()