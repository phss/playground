from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, validates, relationship
from sqlalchemy import Column, Integer, String, Text, ForeignKey

Base = declarative_base()

engine = create_engine("postgresql+psycopg2://vagrant@/pytweet?host=/var/run/postgresql")

Session = sessionmaker()
Session.configure(bind=engine)


class WriteSession():

    def __init__(self):
        self.session = Session()

    def __enter__(self):
        return self.session

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.session.commit()


class Config(Base):
    __tablename__ = 'config'

    id = Column(Integer, primary_key=True)
    app_name = Column(String, nullable=False)


class User(Base):
    __tablename__ = 'users'

    id = Column(Integer, primary_key=True)
    username = Column(String, nullable=False)
    password = Column(String, nullable=False)

    def __init__(self, username, password):
        self.username = username
        self.password = password

    @validates('username', 'password')
    def validate_not_blank(self, key, value):
        if not value:
            raise ValueError("Field %s can't be blank" % key)
        return value


class Tweet(Base):
    __tablename__ = 'tweets'

    id = Column(Integer, primary_key=True)
    user_id = Column(Integer, ForeignKey('users.id'))
    message = Column(Text, nullable=False)
