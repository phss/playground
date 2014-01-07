from app import api
from flask import Flask
app = Flask(__name__)

@app.route("/")
def homepage():
    return api.app_name()
