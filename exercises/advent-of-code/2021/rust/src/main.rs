use advent_of_code::day1;
use std::env;

fn parse_command_line_args() -> (u32, u32) {
    let args: Vec<String> = env::args().collect();
    let parse_numeric = |arg: &String| arg.parse::<u32>();

    match args.len() {
        3 => (
            parse_numeric(&args[1]).expect("invalid day"),
            parse_numeric(&args[2]).expect("invalid part"),
        ),
        _ => panic!("wrong number of args"),
    }
}

fn run(day: u32, part: u32) -> u32 {
    let solution = match (day, part) {
        (1, 1) => day1::part1,
        _ => panic!("No solution yet for day {} and part {}", day, part),
    };
    solution()
}
fn main() {
    let (day, part) = parse_command_line_args();
    let result = run(day, part);
    println!("Day {} part {} answer: {}", day, part, result)
}
