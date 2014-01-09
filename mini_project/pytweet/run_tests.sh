#!/bin/bash

sudo pip install -r requirements.txt


nosetests unit/

./clean_slate_db
nosetests integration/

./clean_slate_db
behave --tags=-wip