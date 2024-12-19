# Usar OpenJDK 21 como base
FROM openjdk:21-jdk

# Establecer una variable para la versión de WildFly y el directorio de instalación
ENV WILDFLY_VERSION=34.0.1.Final
ENV WILDFLY_HOME=/opt/wildfly

# Descargar e instalar WildFly
RUN curl -L -o wildfly.tar.gz https://github.com/wildfly/wildfly/releases/download/${WILDFLY_VERSION}/wildfly-${WILDFLY_VERSION}.tar.gz && \
    tar -xvzf wildfly.tar.gz && \
    mv wildfly-${WILDFLY_VERSION} ${WILDFLY_HOME} && \
    rm wildfly.tar.gz

# Copiar el archivo libreria.war al directorio de despliegue
COPY llibreria.war ${WILDFLY_HOME}/standalone/deployments/

# Cambiar el puerto HTTP a 8085 y que se pueda acceder des de cualquier ip en el archivo standalone.xml
RUN sed -i 's|<socket-binding name="http" port="${jboss.http.port:8080}"/>|<socket-binding name="http" port="${jboss.http.port:8085}"/>|' ${WILDFLY_HOME}/standalone/configuration/standalone.xml && \
    sed -i 's|<inet-address value="${jboss.bind.address.management:127.0.0.1}"/>|<inet-address value="${jboss.bind.address.management:0.0.0.0}"/>|' ${WILDFLY_HOME}/standalone/configuration/standalone.xml && \
    sed -i 's|<inet-address value="${jboss.bind.address:127.0.0.1}"/>|<inet-address value="${jboss.bind.address:0.0.0.0}"/>|' ${WILDFLY_HOME}/standalone/configuration/standalone.xml && \
    sed -i 's|<wsdl-host>${jboss.bind.address:127.0.0.1}</wsdl-host>|<wsdl-host>${jboss.bind.address:0.0.0.0}</wsdl-host>|' ${WILDFLY_HOME}/standalone/configuration/standalone.xml

# Exponer el puerto 8085
EXPOSE 8085

# Establecer el comando para iniciar WildFly
CMD ["sh", "-c", "${WILDFLY_HOME}/bin/standalone.sh -b 0.0.0.0"]