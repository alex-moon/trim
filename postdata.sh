#!/bin/sh
clear;
curl -d "{\"term\":\"$2\",\"score\":$3}"  "http://localhost:8080/wick/$1/term/json/" -H "Content-Type: application/json"
printf "\nDone\n"
