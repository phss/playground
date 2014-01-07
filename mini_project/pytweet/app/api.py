from app.models import make_session, Config

def app_name():
    return make_session().query(Config).first().app_name