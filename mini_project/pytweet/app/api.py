from app.models import *


def app_name():
    return Session().query(Config).first().app_name


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
