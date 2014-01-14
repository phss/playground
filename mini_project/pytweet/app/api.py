from app.models import *


def get_account(username):
    return Session().query(User).filter_by(username=username).first()


def create_account(username, password):
    user = get_account(username)
    if user:
        raise ValueError("There's already an user with the name of %s" % username)

    with WriteSession() as session:
        session.add(User(username, password))


def authenticate(username, password):
    user = get_account(username)
    return user and user.password == password


def create_tweet(user, text):
    with WriteSession() as session:
        session.add(Tweet(user_id=user.id, text=text))


def get_tweets(user):
    return user.tweets