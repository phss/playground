use std::fs;

pub fn part1() -> u32 {
    let input_file = "data/day1.txt";

    // Parse input file
    let contents = fs::read_to_string(input_file).expect("unable to read file");
    let lines = contents.split("\n");
    let report: Vec<u32> = lines
        .map(|line| line.parse().expect("not a number"))
        .collect();

    calculate_increases(report)
}

fn calculate_increases(measurements: Vec<u32>) -> u32 {
    let mut increases = 0;
    for ms in measurements.windows(2) {
        if matches!(ms,  [a, b] if b > a) {
            increases += 1;
        }
    }
    increases
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn increases() {
        let measurements = vec![199, 200, 208, 210, 200, 207, 240, 269, 260, 263];
        assert_eq!(calculate_increases(measurements), 7);
    }
}
