extern crate base64;

use self::base64::encode;
use std::vec::Vec;

type Byte = u8;

pub fn hexstr_to_bytes(string: &str) -> Vec<Byte> {
    fn char_to_byte(c: &[Byte]) -> Byte {
        let things = String::from_utf8(c.to_vec()).unwrap();
        u8::from_str_radix(&things, 16).unwrap()
    }

    string.as_bytes().chunks(2).map(char_to_byte).collect()
}

pub fn bytes_to_base64str(bytes: &[u8]) -> String {
    encode(bytes)
}

pub fn hexstr_to_base64str(string: &str) -> String {
    let bytes = hexstr_to_bytes(&string);
    bytes_to_base64str(&bytes)
}
