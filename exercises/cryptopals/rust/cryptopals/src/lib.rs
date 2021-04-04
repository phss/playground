pub mod converter {
    use core::panic;

    pub fn hex_to_base64(hex_string: String) -> String {
        base64::encode(hex_to_bytes(hex_string))
    }

    fn hex_to_bytes(hex_string: String) -> Vec<u8> {
        let digits: Vec<u8> = hex_string
            .chars()
            .map(|c| c.to_digit(16).unwrap() as u8)
            .collect();

        digits
            .chunks(2)
            .map(|pair| match pair {
                [high, low] => (high << 4) + low,
                [high] => (high << 4),
                _ => panic!("malformed digits"),
            })
            .collect()
    }

    #[cfg(test)]
    mod tests {
        use super::*;

        #[test]
        fn test_hex_to_base64_conversion() {
            assert_eq!(
                String::from("ASNFZ4mrze8="),
                hex_to_base64(String::from("0123456789abcdef"))
            );
        }

        #[test]
        fn test_hex_to_bytes_with_even_length_string() {
            assert_eq!(vec![73, 39, 255], hex_to_bytes(String::from("4927ff")));
        }

        #[test]
        fn test_hex_to_bytes_with_odd_length_string() {
            assert_eq!(vec![73, 39, 240], hex_to_bytes(String::from("4927f")));
        }

        #[test]
        fn test_hex_to_bytes_all_digits() {
            assert_eq!(
                vec![0, 17, 34, 51, 68, 85, 102, 119, 152, 153, 170, 187, 204, 221, 238, 255],
                hex_to_bytes(String::from("00112233445566779899aabbccddeeff"))
            );
        }

        #[test]
        #[should_panic]
        fn test_hex_to_bytes_panics_when_bad_digit() {
            hex_to_bytes(String::from("invalid"));
        }
    }
}
