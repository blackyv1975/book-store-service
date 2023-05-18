#!/bin/bash

docker compose -f compose.yml down

docker volume prune -f

docker system prune -f

rm -rf ~/docker-data/