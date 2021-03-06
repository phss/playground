#!/bin/bash

echo "## Installing postgres"

sudo apt-get -y update
sudo apt-get -y install postgresql
sudo apt-get -y install postgresql-server-dev-all
sudo apt-get -y install pgadmin3
sudo -u postgres createuser --superuser $USER
sudo -u postgres createdb $USER

echo "## Installing python"

sudo apt-get -y install python
sudo apt-get -y install python-dev
sudo apt-get -y install python-pip
