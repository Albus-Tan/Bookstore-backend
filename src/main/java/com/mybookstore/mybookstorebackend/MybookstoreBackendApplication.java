package com.mybookstore.mybookstorebackend;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;


@SpringBootApplication
@EnableNeo4jRepositories
public class MybookstoreBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybookstoreBackendApplication.class, args);
    }

}
