

Comandos do docker utilizados

~~bash
 docker run --name postgres-todo -p 5432:5432 -e POSTGRES_DB=todolist -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres
~~

~~bash
 docker exec -it postgres-todo /bin/bash
~~~