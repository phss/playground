from app import api
from flask import Flask, render_template
app = Flask(__name__)


@app.route("/")
def homepage():
    return render_template('homepage.html', app_name=api.app_name())
