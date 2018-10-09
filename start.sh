#!/bin/bash

cd ~/JavaCP
python3 -m mkdocs serve &
while :
do
    git pull --quiet || true
    sleep 10
done