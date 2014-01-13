#!/bin/bash


# Requirements
sudo pip install -r requirements.txt

# Unit
nosetests unit/
if [ $? -ne 0 ]; then exit; fi

# Integration
./clean_slate_db
nosetests integration/
if [ $? -ne 0 ]; then exit; fi

# Feature
./clean_slate_db
behave --tags=-wip
