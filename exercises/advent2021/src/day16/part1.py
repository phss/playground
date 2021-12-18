def parse(filename: str):
    with open(filename) as file:
        return file.read()

def header(bits: str):
    version = int(bits[0:3], 2)
    type = int(bits[3:6], 2)
    return (version, type)

def literalFrom(bits: str):
    version, type = header(bits)

    literal = ""
    i = 6
    while True:
        control = bits[i]
        literal += bits[i+1:i+5]
        i += 5
        if control == '0':
            break

    while i % 4 != 0:
        i += 1

    print(i)

    return ((version, type, int(literal, 2)), bits[i:])

def packetify(bits: str):
    (literal, bits) = literalFrom(bits)
    print(bits)
    return literal

def solve(input: str) -> int:
    bits = bin(int(input, 16))[2:]
    packets = packetify(bits)
    print(packets)
    return -1

def main():
    input = parse('data/literal.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()