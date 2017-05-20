extern crate base64;

use self::base64::encode;
use std::vec::Vec;

type Byte = u8;

fn hexchar_to_digit(c: char) -> u8 {
    c.to_digit(16).unwrap() as u8
}

fn hexstr_to_bytes(string: &str) -> Vec<Byte> {
    let mut digits = string.chars().map(hexchar_to_digit);
    let mut bytes = Vec::new();
    loop {
        let byte = match (digits.next(), digits.next()) {
            (Some(high), Some(low)) => (high << 4) + low,
            (Some(high), None) => (high << 4),
            _ => break,
        };
        bytes.push(byte);
    }
    bytes
}

fn bytes_to_base64str(bytes: &[u8]) -> String {
    encode(bytes)
}

pub fn hexstr_to_base64str(string: &str) -> String {
    let bytes = hexstr_to_bytes(&string);
    bytes_to_base64str(&bytes)
}

#[cfg(test)]
mod tests {

    #[test]
    fn it_converts_even_sized_hexstring_to_bytes() {
        assert_eq!(vec![73, 39, 255], super::hexstr_to_bytes("4927ff"));

    }

    #[test]
    fn it_converts_odd_sized_hexstring_to_bytes() {
        assert_eq!(vec![73, 39, 240], super::hexstr_to_bytes("4927f"));
    }
}
