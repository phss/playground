from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, validates
from sqlalchemy import Column, Integer, String

Base = declarative_base()

engine = create_engine("postgresql+psycopg2://vagrant@/pytweet?host=/var/run/postgresql")

Session = sessionmaker()
Session.configure(bind=engine)


class Config(Base):
    __tablename__ = 'config'

    config_id = Column(Integer, primary_key=True)
    app_name = Column(String, nullable=False)


class User(Base):
    __tablename__ = 'users'

    user_id = Column(Integer, primary_key=True)
    username = Column(String, nullable=False)
    password = Column(String, nullable=False)

    @validates('username', 'password')
    def validate_not_blank(self, key, value):
        if not value:
            raise ValueError("Field %s can't be blank" % key)
        return value