FROM prepared_wildfly:latest

ADD customization /opt/jboss/wildfly/customization/
#COPY customization /opt/jboss/wildfly/customization/ietservice.war

EXPOSE 8080 9990

CMD ["/opt/jboss/wildfly/customization/execute.sh"]
