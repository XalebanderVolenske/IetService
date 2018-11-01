#!/bin/bash

# Usage: execute.sh [WildFly mode] [configuration file]
#
# The default mode is 'standalone' and default configuration is based on the
# mode. It can be 'standalone.xml' or 'domain.xml'.

echo "=> Executing Customization script"

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

#CONNECTION_URL=jdbc:mysql://$MYSQL_CONTAINER_PORT_3306_TCP_ADDR:$MYSQL_CONTAINER_PORT_3306_TCP_PORT/$MYSQL_DATABASE
CONNECTION_URL=jdbc:mysql://iet_mariadb:3306/testdb
echo "Connection URL:" $CONNECTION_URL

echo "=> Hosts"
cat /etc/hosts
echo
echo "=> Umgebungsvariablen (env)"
env

function wait_for_server() {
  until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do
    sleep 1
    echo .
  done
}

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

echo "=> Starting WildFly server"
#$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG > /dev/null &
echo $JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -c $JBOSS_CONFIG
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG &

echo "=> Waiting for the server to boot"
wait_for_server

$JBOSS_CLI -c << EOF
batch

# Add MySQL module
module add --name=com.mysql --resources=/opt/jboss/wildfly/customization/mysql-connector-java-5.1.42-bin.jar --dependencies=javax.api,javax.transaction.api

# Add MySQL driver
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource)

# Add the datasource
data-source add --name=mysqlDS --driver-name=mysql --jndi-name=java:jboss/datasources/ExampleMySQLDS --connection-url=$CONNECTION_URL?useUnicode=true&amp;characterEncoding=UTF-8 --user-name=$MYSQL_USER --password=$MYSQL_PASSWORD --use-ccm=false --max-pool-size=25 --blocking-timeout-wait-millis=5000 --enabled=true
#data-source add --name=mysqlDS --driver-name=mysql --jndi-name=java:jboss/datasources/ExampleMySQLDS --connection-url=$CONNECTION_URL?useUnicode=true&amp;characterEncoding=UTF-8 --user-name=root --password=passme --use-ccm=false --max-pool-size=25 --blocking-timeout-wait-millis=5000 --enabled=true
#/subsystem=datasources/data-source=mydb/:add(connection-url=jdbc:mysql://mysql/mydb,driver-name=mysql,jndi-name=java:jboss/datasources/ExampleMySQLDS,initial-pool-size=4,max-pool-size=64,min-pool-size=4,password=$MYSQL_PASSWORD ,user-name=$MYSQL_USER)

# Execute the batch
run-batch
EOF

# Deploy the WAR
echo Deploy the war
echo cp /opt/jboss/wildfly/customization/ietservice.war $JBOSS_HOME/$JBOSS_MODE/deployments/ietservice.war
cp /opt/jboss/wildfly/customization/ietservice.war $JBOSS_HOME/$JBOSS_MODE/deployments/ietservice.war
echo touch $JBOSS_HOME/$JBOSS_MODE/deployments/ietservice.war.dodeploy
touch $JBOSS_HOME/$JBOSS_MODE/deployments/ietservice.war.dodeploy
ls $JBOSS_HOME/$JBOSS_MODE/deployments
#cat $JBOSS_HOME/$JBOSS_MODE/deployments/README.txt

echo "=> Shutting down WildFly"
if [ "$JBOSS_MODE" = "standalone" ]; then
  $JBOSS_CLI -c "shutdown"
else
  $JBOSS_CLI -c "/host=*:shutdown"
fi

echo "=> Restarting WildFly"
echo $JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -c $JBOSS_CONFIG
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b=0.0.0.0 -bmanagement=0.0.0.0 -c=$JBOSS_CONFIG
#> /dev/null
