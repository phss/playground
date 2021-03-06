from app import api
from flask import Flask, render_template, request, session, redirect, url_for

app = Flask(__name__)
app.secret_key = 'super-secret'

def user_from_session():
    username = session.get('logged_in_user')
    if username:
        return api.get_account(username)
    else:
        return None

@app.route('/')
def homepage():
    user = user_from_session()
    tweets = api.get_tweets(user) if user else None
    return render_template('homepage.html', user=user, tweets=tweets)


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
        return log_user()
    else:
        return render_template('login.html')


def log_user():
    username, password = request.form['username'], request.form['password']
    if api.authenticate(username, password):
        session['logged_in_user'] = username
        return redirect(url_for('homepage'))
    else:
        return render_template('failure.html', message='Failed to authenticate')


@app.route('/logout', methods=['GET', 'POST'])
def logout():
    session.pop('logged_in_user', None)
    return redirect(url_for('homepage'))


@app.route('/tweet', methods=['GET', 'POST'])
def tweet():
    if request.method == 'POST':
        return create_tweet()
    else:
        return render_template('tweet.html')

def create_tweet():
    user = user_from_session()
    if user:
        api.create_tweet(user, request.form['text'])
        return redirect(url_for('homepage'))
    else:
        return render_template('failure.html', message='Login before tweeting')