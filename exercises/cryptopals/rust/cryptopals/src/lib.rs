pub mod converter {
    use crate::hex;

    pub fn hex_to_base64(hex_string: String) -> String {
        base64::encode(hex::decode(hex_string).unwrap())
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
    #[derive(Debug, Clone, Copy, PartialEq)]
    pub enum DecodeError {
        InvalidChar(char),
    }

    pub fn decode(string: String) -> Result<Vec<u8>, DecodeError> {
        let to_byte = |c: char| {
            c.to_digit(16)
                .map(|digit| digit as u8)
                .ok_or(DecodeError::InvalidChar(c))
        };
        let half_bytes: Result<Vec<u8>, DecodeError> = string.chars().map(to_byte).collect();

        half_bytes?
            .chunks(2)
            .map(|pair| match pair {
                [high, low] => Ok((high << 4) + low),
                [high] => Ok(high << 4),
                _ => panic!("should never reach here"),
            })
            .collect()
    }

    #[cfg(test)]
    mod tests {
        use super::*;

        #[test]
        fn test_decode() {
            assert_eq!(
                Ok(vec![73, 39, 255]),
                decode(String::from("4927ff")),
                "even string"
            );
            assert_eq!(
                Ok(vec![73, 39, 240]),
                decode(String::from("4927f")),
                "odd string"
            );
            assert_eq!(
                Ok(vec![
                    0, 17, 34, 51, 68, 85, 102, 119, 152, 153, 170, 187, 204, 221, 238, 255
                ]),
                decode(String::from("00112233445566779899aabbccddeeff")),
                "all digits"
            );
        }

        #[test]
        fn test_decode_with_bad_digit() {
            assert_eq!(
                Err(DecodeError::InvalidChar('i')),
                decode(String::from("invalid"))
            );
        }
    }
}
