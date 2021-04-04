pub mod converter {
    use crate::hex;

    pub fn hex_to_base64(hex_string: String) -> String {
        base64::encode(hex::decode(hex_string))
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
    }
}

mod hex {
    pub fn decode(hex_string: String) -> Vec<u8> {
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
        fn test_decode() {
            assert_eq!(
                vec![73, 39, 255],
                decode(String::from("4927ff")),
                "even string"
            );
            assert_eq!(
                vec![73, 39, 240],
                decode(String::from("4927f")),
                "odd string"
            );
            assert_eq!(
                vec![0, 17, 34, 51, 68, 85, 102, 119, 152, 153, 170, 187, 204, 221, 238, 255],
                decode(String::from("00112233445566779899aabbccddeeff")),
                "all digits"
            );
        }

        #[test]
        #[should_panic]
        fn test_decode_panics_when_bad_digit() {
            decode(String::from("invalid"));
        }
    }
}
