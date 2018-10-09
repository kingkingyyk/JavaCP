#!/bin/bash

mkdocs serve &
while :
do
    git pull --quiet || true
    sleep 10
done