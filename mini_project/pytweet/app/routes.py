from app import api
from flask import Flask, render_template, request, session, redirect, url_for

app = Flask(__name__)
app.secret_key = 'super-secret'


@app.route('/')
def homepage():
    return render_template('homepage.html', app_name=api.app_name())

@app.route('/create-account', methods=['GET', 'POST'])
def account_page():
    if request.method == 'POST':
        return create_account()
    else:
        return render_template('create_account.html')


def create_account():
    username, password = request.form['username'], request.form['password']
    try:
        api.create_account(username, password)
        return render_template('account_created.html', username=username)
    except ValueError as e:
        return render_template('failure.html', message=e.message)


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        session['logged_in_user'] = request.form['username']
        return redirect(url_for('homepage'))
    else:
        return render_template('login.html')
