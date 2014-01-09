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
        username = request.form['username']
        password = request.form['password']
        try:
            api.create_account(username, password)
            return render_template('account_created.html', username=username)
        except ValueError as e:
            return render_template('failure.html', message=e.message)