#!/bin/bash

# Requirements
sudo pip install -r requirements.txt

# Unit
nosetests unit/

# Integration
./clean_slate_db
nosetests integration/

# Feature
./clean_slate_db
behave --tags=-wip
