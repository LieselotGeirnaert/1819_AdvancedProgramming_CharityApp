# Using Docker

## Creating the container

Go to the root of the repo and execute this command:

### Linux
```shell
docker run --name jhipster -v ./:/home/jhipster/app -p 8080:8080 -p 9000:9000 -p 3001:3001 -d -t jhipster/jhipster
```

### Windows
```shell
docker run --name jhipster -v "${PWD}:/home/jhipster/app" -p 8080:8080 -p 9000:9000 -p 3001:3001 -d -t jhipster/jhipster
```

## Starting the container
```shell
docker start jhipster
```

## Stopping the container
```shell
docker stop jhipster
```

## Bash
```shell
docker exec -it jhipster bash
docker exec -it -u root jhipster bash
```

## Removing the container
```shell
docker rm jhipster
```