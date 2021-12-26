from typing import Tuple

input = ((211, 232), (-124, -69))
# input = ((20, 30), (-10, -5))
horizontal, vertical = input
startX, endX = horizontal
endY, startY = vertical

def hits(velY: int) -> Tuple[bool, int]:
    height = 0
    y = 0
    while y >= endY:
        if y <= startY:
            return (True, height)
        y += velY
        if y > height:
            height = y
        velY -= 1
    return (False, height)

def solve() -> int:
    maxHeight = 0

    for velocity in range(1000):
        valid, height = hits(velocity)
        if valid and height > maxHeight:
            maxHeight = height

    return maxHeight

def main():
    result = solve()
    print(result)

if __name__ == '__main__':
    main()