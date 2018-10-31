docker run -e MYSQL_USER=root -e MYSQL_PASSWORD=passme -e MYSQL_DATABASE=testdb --link iet_mariadb:mysql_container -p 8080:8080 -p 9990:9990 --name iet_service ooe/iet_service
docker logs iet_service