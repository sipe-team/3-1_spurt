#bash

users=200

echo "docker-compose run --rm k6 run /k6-scripts/week5/get-order-sync.js -e USERS=$users"
docker-compose run --rm k6 run /k6-scripts/week5/get-order-sync.js  -e USERS=$users
sleep 30 s

echo "docker-compose run --rm k6 run /k6-scripts/week5/get-order-async.js -e USERS=$users"
docker-compose run --rm k6 run /k6-scripts/week5/get-order-async.js  -e USERS=$users
sleep 30 s

echo "docker-compose run --rm k6 run /k6-scripts/week5/get-order-join.js -e USERS=$users"
docker-compose run --rm k6 run /k6-scripts/week5/get-order-join.js -e USERS=$users
sleep 30 s

echo "docker-compose run --rm k6 run /k6-scripts/week5/get-order-sync.js  -e USERS=$users"
docker-compose run --rm k6 run /k6-scripts/week5/get-order-sync.js -e USERS=$users
sleep 30 s
