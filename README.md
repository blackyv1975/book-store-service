This is a Book Store, reactive (WebFlux) Spring Boot application with PostgreSQL database and R2DBC.

Compile a native executable:
```bash
mvnd -Pnative native:compile 
```

Build a native image:
```bash
mvnd -Pnative spring-boot:build-image
```

Build and run application with tracing agent attached:<br>
(Exercise the code paths you want to have hints for and then stop the application with ctrl-c.)
```bash
mvnd clean install &&
mvnd -Pnative native:compile &&
java -Dspring.aot.enabled=true \
    -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image \
    -jar target/book-store-service-0.0.1-SNAPSHOT.jar
```