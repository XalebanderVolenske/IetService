FROM jboss/wildfly:14.0.0.Final

RUN /opt/jboss/wildfly/bin/add-user.sh admin passme --silent

USER root

RUN yum -y install bash mysql && \
    yum clean all && \
    rm -rf /var/cache/yum /var/log/yum.log




