# String operations
def fromString(numberStr):
    return eval(numberStr)

def toString(number):
    if type(number) == int:
        return str(number)
    else:
        left, right = number
        return "[%s,%s]" % (toString(left), toString(right))

# Tree operations
def addToLeftmost(number, toAdd):
    if type(number) == int:
        return number + toAdd
    else:
        left, right = number
        return [addToLeftmost(left, toAdd), right]

def addToRightmost(number, toAdd):
    if type(number) == int:
        return number + toAdd
    else:
        left, right = number
        return [left, addToRightmost(right, toAdd)]


def explode(number, level=0):
    if type(number) == int:
        return (number, (False, [None, None]))
    elif level == 4:
        return (0, (True, number))

    left, right = number

    left, (leftExploded, (toLeft, toRight)) = explode(left, level + 1)
    if leftExploded:
        return ([left, addToLeftmost(right, toRight)], (True, [toLeft, 0]))

    right, (rightExploded, (toLeft, toRight)) = explode(right, level + 1)
    if rightExploded:
        return ([addToRightmost(left, toLeft), right], (True, [0, toRight]))

    return ([left, right], (False, [None, None]))


def reduce(number):
    goOn = True
    while goOn:
        number, (exploded, _) = explode(number)
        goOn = exploded
    return number

def add(a, b):
    return reduce([a, b])

# Core
def solve() -> int:
    # print(toString(reduce(fromString("[[[[[9,8],1],2],3],4]"))))
    print(toString(reduce(fromString("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]"))))
    return 0

def main():
    result = solve()
    print(result)

if __name__ == '__main__':
    main()