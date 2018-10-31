docker run --name iet_mariadb -e MYSQL_ROOT_PASSWORD=passme -d -v ~/docker/data/mysql:/var/lib/mysql -p 4306:3306 mariadb:latest
