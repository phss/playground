extern crate base64;

use base64::encode;
use std::vec::Vec;

fn main() {
    let input = "49276d206b696c6c696e6720796f757220627261696e20\
                 6c696b65206120706f69736f6e6f7573206d757368726f6f6d";

    let mut bytes = Vec::new();

    for n in input.as_bytes().chunks(2) {
        let chars = String::from_utf8(n.to_vec()).unwrap();
        let byte = u8::from_str_radix(&chars, 16);
        bytes.push(byte.unwrap());
    }

    let b64 = encode(&bytes);

    println!("{:?}", b64);
}
