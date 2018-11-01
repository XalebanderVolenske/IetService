# IetService

## Neues Image für Wildfly

Für den Application Server Wildfly wurde ein Zwischen-Image erstellt.

Dieses Image beinhaltet die Installation des mysql-clients, der zur Überprüfung verwendet wird, ob die Datenbank schon verfügbar ist.

Mit dem Skript `build_prepared_wildfly.sh` wird ein ev. vorhandenes Image gelöscht und ein neues Image gebuildet.
Dieses Image basiert auf Wildfly 14.


Dockerfile: 
```
FROM jboss/wildfly:14.0.0.Final

RUN /opt/jboss/wildfly/bin/add-user.sh admin passme --silent

USER root

RUN yum -y install bash mysql && \
    yum clean all && \
    rm -rf /var/cache/yum /var/log/yum.log
```

Da die zeitinsive Installation des mysql-clients nun nur einmal durchgeführt werden muss, erfolgt das Deployment sehr rasch.

## Eigenes Image für das Deployment des war-Files

Dockerfile:
```
FROM prepared_wildfly:latest

ADD customization /opt/jboss/wildfly/customization/

EXPOSE 8080 9990

CMD ["/opt/jboss/wildfly/customization/execute.sh"]

```

Hier werden nur noch das war-File, der jdbc-Treiber und das Konfigurationsskript 'execute.sh' in das Docker-Image kopiert und schließlich das Konfigurationsskript gestartet.

Zu beachten ist, daß während des Hochfahrens auf die Verfügbarkeit der Datenbank gewartet wird

```
function wait_for_db() {
    maxcounter=60

    counter=1
    while ! mysql -h iet_mariadb -u"root" -p"$MYSQL_ROOT_PASSWORD" -e "show databases;"; do
        echo "waiting for Godot..."
        sleep 1
        counter=`expr $counter + 1`
        if [ $counter -gt $maxcounter ]; then
            >&2 echo "We have been waiting for MySQL too long already; failing."
            exit 1
        fi;
    done
    echo "yeah, mysql available, so lets go..."

}
wait_for_db
```


## docker-compose

Docker-compose orchestriert die einzelnen Container, dh zuerst wird die Datenbank getstartet und wenn dieser Container verfügbar ist,
wird der Application Server gestartet. Da nach dem OS auch die DB hochfahren muß, ist trotzdem obige Funktion `wait_for_db` notwendig.

docker-compose.yml
```bash
# JEE Aplication Stack for Development
# run docker-compose up --build from the commandline

version: '2.0'

services:
  iet_service:
    build: docker/deploy
    ports:
      - 8080:8080
      - 9990:9990
    depends_on:
      - iet_mariadb
    environment:
      - MYSQL_USER=root
      - MYSQL_PASSWORD=passme
      - MYSQL_ROOT_PASSWORD=passme
      - MYSQL_DATABASE=testdb

  iet_mariadb:
    image: mariadb
    ports:
      - 3306:3306
    volumes:
      - ~/docker/data/mysql:/var/lib/mysql
    environment:
      - MYSQL_USER=root
      - MYSQL_PASSWORD=passme
      - MYSQL_ROOT_PASSWORD=passme
      - MYSQL_DATABASE=testdb
```

## Skripts

Folgendes Scripts stehen zur Verfügung:

Script | Zweck
---|---
compile.sh | Kompilieren des Projekts. Das war-File wird in das customization-Verzeichnis verschoben
build_prepared_wildfly | Löschen des Images mit anschließender Neuerstellung
up.sh | Build und starten des docker-compose
down.sh | Beenden und Löschen der Container

mit 'docker-compose start' und 'docker-compose stop' werden beide Container gestoppt und gestartet.