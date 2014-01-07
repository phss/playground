from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy import Column, Integer, String

Base = declarative_base()

engine = create_engine("postgresql+psycopg2://vagrant@/pytweet?host=/var/run/postgresql")

def session():
    Session = sessionmaker()
    Session.configure(bind=engine)
    return Session()

class Config(Base):
    __tablename__ = 'config'

    config_id = Column(Integer, primary_key=True)
    app_name = Column(String, nullable=False)
