extern crate hyper;

use std::io::Read;
use hyper::server::{Server, Request, Response};
use hyper::Client;

fn proxy_request(url: &str) -> hyper::client::Response {
    let response = Client::new().get(url).send().unwrap();
    println!("Go {} from {}!", response.status, url);
    response
}

fn main() {
    fn hello(_: Request, res: Response) {
        let mut proxy_res = proxy_request("http://example.com");

        let mut body = String::new();
        proxy_res.read_to_string(&mut body).unwrap();

        res.send(body.as_bytes()).unwrap();
    }

    Server::http("0.0.0.0:5000")
        .unwrap()
        .handle(hello)
        .unwrap();
}
