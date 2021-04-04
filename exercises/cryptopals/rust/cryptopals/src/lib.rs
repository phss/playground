pub mod converter {
    pub fn hex_to_base64(input: String) -> String {
        let mut digits = input.chars().map(|c| c.to_digit(16).unwrap() as u8);
        let mut bytes = Vec::new();
        loop {
            let byte = match (digits.next(), digits.next()) {
                (Some(high), Some(low)) => (high << 4) + low,
                (Some(high), None) => (high << 4),
                _ => break,
            };
            bytes.push(byte);
        }
        base64::encode(bytes)
    }

    #[cfg(test)]
    mod tests {
        use super::*;

        #[test]
        fn test_hex_to_bytes_conversion() {
            assert_eq!(
                String::from("ASNFZ4mrze8="),
                hex_to_base64(String::from("0123456789abcdef"))
            );
        }
    }
}
