FROM jboss/wildfly
 
ADD target/CropyWeb-1.0.war /opt/jboss/wildfly/standalone/deployments/
 
EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
