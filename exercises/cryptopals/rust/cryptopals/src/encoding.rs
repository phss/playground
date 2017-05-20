extern crate base64;

use self::base64::encode;
use std::vec::Vec;

fn hexchar_to_digit(c: char) -> u8 {
    c.to_digit(16).expect("not an hexadecimal char") as u8
}

fn hexstr_to_bytes(string: &str) -> Vec<u8> {
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
    fn it_converts_all_hexchars_to_digits() {
        assert_eq!(0, super::hexchar_to_digit('0'));
        assert_eq!(1, super::hexchar_to_digit('1'));
        assert_eq!(2, super::hexchar_to_digit('2'));
        assert_eq!(3, super::hexchar_to_digit('3'));
        assert_eq!(4, super::hexchar_to_digit('4'));
        assert_eq!(5, super::hexchar_to_digit('5'));
        assert_eq!(6, super::hexchar_to_digit('6'));
        assert_eq!(7, super::hexchar_to_digit('7'));
        assert_eq!(8, super::hexchar_to_digit('8'));
        assert_eq!(9, super::hexchar_to_digit('9'));
        assert_eq!(10, super::hexchar_to_digit('a'));
        assert_eq!(11, super::hexchar_to_digit('b'));
        assert_eq!(12, super::hexchar_to_digit('c'));
        assert_eq!(13, super::hexchar_to_digit('d'));
        assert_eq!(14, super::hexchar_to_digit('e'));
        assert_eq!(15, super::hexchar_to_digit('f'));
    }

    #[test]
    #[should_panic(expected = "not an hexadecimal char")]
    fn it_should_panic_when_converting_non_hexchar() {
        super::hexchar_to_digit('g');
    }

    #[test]
    fn it_converts_even_sized_hexstring_to_bytes() {
        assert_eq!(vec![73, 39, 255], super::hexstr_to_bytes("4927ff"));

    }

    #[test]
    fn it_converts_odd_sized_hexstring_to_bytes() {
        assert_eq!(vec![73, 39, 240], super::hexstr_to_bytes("4927f"));
    }
}
