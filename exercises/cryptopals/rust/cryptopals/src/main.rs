extern crate base64;

use base64::encode;
use std::vec::Vec;

fn main() {
    let input = "49276d206b696c6c696e6720796f757220627261696e20\
                 6c696b65206120706f69736f6e6f7573206d757368726f6f6d";

    fn char_to_byte(c: &[u8]) -> u8 {
        let things = String::from_utf8(c.to_vec()).unwrap();
        u8::from_str_radix(&things, 16).unwrap()
    }

    let bytes: Vec<u8> = input.as_bytes().chunks(2).map(char_to_byte).collect();

    let b64 = encode(&bytes);

    println!("{:?}", b64);
}
