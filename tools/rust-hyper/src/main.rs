extern crate hyper;

use hyper::server::{Server, Request, Response};

fn main() {
    fn hello(_: Request, res: Response) {
        res.send(b"hello world").unwrap();
    }

    Server::http("0.0.0.0:5000").unwrap().handle(hello).unwrap();
}
