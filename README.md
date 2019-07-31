# Advisor

Project to get advices from Assets, using microservices.


### Services and port numbers

| Service               | Port Number            |
|-----------------------|----------------        |
| Postgres              |            5444        |
| Redis                 |            6379        |
| Config Server         |            8888 - 8889 |
| Discovery Server      |            9777 - 9799 |
| Advisor Service       |            9888 - 9899 |
| Advisor Auth Service  |            9666 - 9699 |
| Frontend              |            4200 |
| Nginx Interface       |            5301 |


### Start 

```sh
docker-compose up --build
```

### Utilities

https://docs.docker.com/compose/startup-order/

