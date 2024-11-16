This will configure NGINX to forward the request from port `4000` to `http://microservice:8080`. 

This will then be resolved by [Docker’s embedded DNS server](https://docs.docker.com/v17.09/engine/userguide/networking/configure-dns/), which will use a [round robin](https://en.wikipedia.org/wiki/Round-robin_DNS) implementation to resolve the DNS requests based on the service name and distribute them to the Docker containers.