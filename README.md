# MonTAPI (MONey Transfer API)
Simple Java-spring example for a REST web service api.

#Description
This api is developed with Spring boot and can be run as a standalone application. For test, we are using an embedded database H2.
The api is using
   - SpringMVC for the rest implementation, 
   - JPA/Hibernate for the access to the DBs,
   - Maven for the build and depedencies management
   - SpringJUnit for the test and MockMvc
   
Regarding the api documentation, a jsondoc is generated on the startup of the server and is available on the following url <a>http://localhost:8080/jsondoc</a>. 
It can be displayed in a ui by using the embedded tools jsondoc-ui on the following url <a>http://localhost:8080/jsondoc-ui.html</>

# Prerequisite
JAVA version: 8
Maven: 3

# Starting
download or clone or simply import the source code.
Then go the root folder of the project and run the following command line to package the application
```mvn clean package```

Once the application is packaged, the application can be started with the following command line:
```java -jar target/montapi-0.1.0.jar```

#TODO
The api documentation is a nice interface where the client can test the api by posting some data with the form. But to generate the api client it would be great if the application provides a WADL like with Jersey.
Need to implement or to find a tool which handle this problematic.
