from app import api
from flask import Flask, render_template, request
app = Flask(__name__)


@app.route('/')
def homepage():
    return render_template('homepage.html', app_name=api.app_name())

@app.route('/create-account', methods=['GET', 'POST'])
def create_account():
    if request.method == 'GET':
        return render_template('create_account.html')
    else:
        api.create_account(request.form['username'], request.form['password'])
        return render_template('account_created.html')