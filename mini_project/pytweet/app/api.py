from app.models import *


def app_name():
    return Session().query(Config).first().app_name


def create_account(username, password):
    user = Session().query(User).filter_by(username=username).first()
    if user:
        raise ValueError("There's already an user with the name of %s" % username)

    session = Session()
    session.add(User(username=username, password=password))
    session.commit()
