#!/bin/bash

sudo pip install -r requirements.txt

./clean_slate_db

nosetests unit/

behave --tags=-wip