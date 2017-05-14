
fn main() {
    let input = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
    //let result = input.from_hex().unwrap().to_base64(base64::STANDARD);

    //let chars: Vec<char> = input.chars().collect();
    for n in input.as_bytes().chunks(2) {
        let thing = String::from_utf8(n.to_vec()).unwrap();
        println!("{:?}", u8::from_str_radix(thing.as_str(), 16));
    }
}
