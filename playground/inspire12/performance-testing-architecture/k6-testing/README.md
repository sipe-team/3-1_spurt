# For local de-contenerized testing

```shell
k6 run ./js/<test-name>.js -e USERS=20 -e PORT=8080
```

# For container test run with:

```shell
docker-compose run --rm k6 run /k6-scripts/<test-name> -e TYPE=jdbc -e USERS=20
```

Before each test you should run the following script to make every load test independent of the previous executions.

```sql
delete from books b where b.book_id <> 1;
delete from orders ;
```

Datas will be available on the grafana dashboard reachable
at: http://localhost:3000/d/k6/k6-load-testing-results?orgId=1&refresh=5s
