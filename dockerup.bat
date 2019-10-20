#!/bin/sh

docker-compose -f docker-compose.yaml up >> output.log
echo 'Docker is up'