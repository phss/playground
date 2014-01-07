from app.models import make_session, Config

from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
    config = make_session().query(Config).first()

    return config.app_name
