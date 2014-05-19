#!/bin/sh
clear;
curl -d '{"success":"success"}'  "http://localhost:8080/wick/$1/term/$2/$3/"
printf "\nDone\n"
