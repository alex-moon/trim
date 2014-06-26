#!/bin/sh
clear;
curl -d '{"success":"success"}'  "http://localhost:8080/trim/$1/term/$2/$3/"
printf "\nDone\n"
