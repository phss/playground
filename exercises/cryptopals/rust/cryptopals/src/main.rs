extern crate base64;

mod encoding;

fn main() {
    let input = "49276d206b696c6c696e6720796f757220627261696e20\
                 6c696b65206120706f69736f6e6f7573206d757368726f6f6d";

    let bytes = encoding::hexstr_to_bytes(&input);
    let b64 = encoding::bytes_to_base64str(&bytes);

    println!("{:?}", b64);
}
