extern crate base64;

mod encoding;

fn main() {
    let input = "49276d206b696c6c696e6720796f757220627261696e20\
                 6c696b65206120706f69736f6e6f7573206d757368726f6f6d";

    println!("{}", encoding::hexstr_to_base64str(input));
}
