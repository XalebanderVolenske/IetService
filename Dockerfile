FROM jboss/wildfly:10.1.0.Final

RUN /opt/jboss/wildfly/bin/add-user.sh admin passme --silent

ADD customization /opt/jboss/wildfly/customization/
COPY target/ietservice.war /opt/jboss/wildfly/customization/ietservice.war

CMD ["/opt/jboss/wildfly/customization/execute.sh"]
