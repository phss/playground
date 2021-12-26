import math

def parse(filename: str):
    with open(filename) as file:
        return file.read()

def header(bits: str, i: int):
    version = int(bits[i:i+3], 2)
    type = int(bits[i+3:i+6], 2)
    return (version, type)

def literalFrom(bits: str, i: int):
    version, type = header(bits, i)
    i += 6

    literal = ""
    while True:
        control = bits[i]
        literal += bits[i+1:i+5]
        i += 5
        if control == '0':
            break

    return ((version, type, int(literal, 2)), i)

def lenghtSubpacketsFrom(bits: str, i: int):
    version, type = header(bits, i)
    i += 7

    length = int(bits[i:i+15], 2)
    i += 15

    subpacketsEnd = i + length
    subpackets = []
    while i < subpacketsEnd:
        packet, i = packetify(bits, i)
        subpackets.append(packet)

    return ((version, type, subpackets), i)

def numberSubpacketsFrom(bits: str, i: int):
    version, type = header(bits, i)
    i += 7

    number = int(bits[i:i+11], 2)
    i += 11

    subpackets = []
    for _ in range(number):
        packet, i = packetify(bits, i)
        subpackets.append(packet)

    return ((version, type, subpackets), i)

def packetify(bits: str, i: int):
    _, type = header(bits, i)
    if type == 4:
        return literalFrom(bits, i)

    if bits[i+6] == '0':
        return lenghtSubpacketsFrom(bits, i) 
    else:
        return numberSubpacketsFrom(bits, i) 

def solve(input: str) -> int:
    bits = format(int(input, 16), "0" + str(len(input) * 4) + "b")
    packets, i = packetify(bits, 0)
    print(packets, i)

    def evaluate(packet):
        version, type, value = (packet)
        if type == 0:
            return sum(map(evaluate, value))
        if type == 1:
            return math.prod(map(evaluate, value))
        if type == 2:
            return min(map(evaluate, value))
        if type == 3:
            return max(map(evaluate, value))
        elif type == 4:
            return value
        if type == 5:
            a, b = map(evaluate, value)
            if a > b:
                return 1
            return 0
        if type == 6:
            a, b = map(evaluate, value)
            if a < b:
                return 1
            return 0
        if type == 7:
            a, b = map(evaluate, value)
            if a == b:
                return 1
            return 0
        return -1


    return evaluate(packets)

def main():
    input = parse('data/day16.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()