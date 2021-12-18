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

def packetify(bits: str, i: int):
    _, type = header(bits, i)
    if type == 4:
        return literalFrom(bits, i)

    return lenghtSubpacketsFrom(bits, i) 

def solve(input: str) -> int:
    bits = format(int(input, 16), "0" + str(len(input) * 4) + "b")
    packets, i = packetify(bits, 0)
    print(packets, i)

    def versionSum(packet):
        version, type, value = (packet)
        versions = [version]
        if type != 4:
            for subpacket in value:
                versions.append(versionSum(subpacket))
        return sum(versions)


    return versionSum(packets)

def main():
    input = parse('data/lengthzero.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()