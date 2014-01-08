from app.models import *


def app_name():
    return Session().query(Config).first().app_name


def create_account(username, password):
    session = Session()
    session.add(User(username=username, password=password))
    session.commit()
