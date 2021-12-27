def parse(filename: str):
    with open(filename) as file:
        return file.read().split('\n')

def pp(seafloor):
    for row in seafloor:
        print(row)
    
def solve(input) -> int:
    pp(input)
    return -1

def main():
    input = parse('data/temp.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()