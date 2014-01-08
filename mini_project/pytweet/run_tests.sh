#!/bin/bash

sudo pip install -r requirements.txt

./clean_slate_db

nosetests unit/

nosetests integration/

behave --tags=-wip