# spring-boot-jdbc-vs-r2dbc

This repository contains the code to be able to test the different performances between a spring-boot blocking application based on JDBC 
and a spring web-flux application wich uses R2DBC reactive drivers.

The experimental results I've found have been published in the following medium article for you to consider.

https://link.medium.com/CzFp1qZKhmb

Please consider that all this code is published under the GNU General Public License v3.0 and it's free for you to use and customize as you wish.

![](img/load_test.png)

### Step 0:
Build gradle-wrapper.jar by executing gradle wrapper inside spring project

```shell
gradle wrapper 
```

### Step 1:
Run the containers by executing the following command inside the "deployment" folder:

```shell
docker-compose rm -f
docker-compose up --build --force-recreate --remove-orphans 
```

![](img/containers.jpg)

### Step 2:

Run k6 tests in temporary container by executing the following command inside the "k6-testing" folder:

```shell
docker-compose run --rm k6 run /k6-scripts/<test-name> -e TYPE=jdbc -e USERS=20
```

![](img/k6-starting.png)

Before each test you should run the following script to make every load test independent of the previous executions.

```sql
delete from books b where b.book_id <> 1;
delete from orders ;
```

### Step 3:

Datas will be available on the grafana dashboard at: 

http://localhost:3000/d/k6/k6-load-testing-results?orgId=1&refresh=5s

![](test-specimens-old/containers-test3/r2dbc.png)
