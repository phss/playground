use encoding;

#[test]
fn challenge_1() {
    let input = "49276d206b696c6c696e6720796f757220627261696e206\
                 c696b65206120706f69736f6e6f7573206d757368726f6f6d";
    let expected_output = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";

    assert_eq!(expected_output, encoding::hexstr_to_base64str(input));
}
