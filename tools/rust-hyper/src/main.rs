extern crate hyper;

use std::io::Read;
use hyper::server::{Server, Request, Response};
use hyper::Client;

fn main() {
    fn hello(_: Request, res: Response) {
        let client = Client::new();

        let mut proxy_res = client.get("http://example.com").send().unwrap();

        let mut body = String::new();
        proxy_res.read_to_string(&mut body).unwrap();

        res.send(body.as_bytes()).unwrap();
    }

    Server::http("0.0.0.0:5000")
        .unwrap()
        .handle(hello)
        .unwrap();
}
