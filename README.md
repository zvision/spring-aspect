
# spring-aspect
Simple Spring Boot project implementing AOP (Aspect-oriented Programming)

Spring Boot 3.2.2 </br>
Java 17 </br>

Hur/vad göra
============

- Att bygga: mvn clean package </br> 
- Att köra:  mvn spring-boot:run    (java -jar target\spring-aspect-0.0.1-SNAPSHOT.jar) </br>

- Debug: mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5000 -Dserver.port=8080"
 och sen i Eclipse meny -välj  "Debug configurations..." -> och skapa en "Remote Java Application",  
 sätt parametrarna Host: localhost, Port: 5000


In-memory databasen </br>
----------------------------
Driver Class: org.h2.Driver </br>
JDBC URL: jdbc:h2:mem:testdb </br>
User Name: sa </br>
Password: password </br>

http://localhost:8080/h2-console/login.do  </br>


Endpoints </br>
-------------- </br>

GET: </br>
curl  http://localhost:8080/customer/get/1 </br>

POST: </br>
curl -X POST -d "{\"firstName\":\"Graham\",\"lastName\":\"Bell\",\"email\":\"graham.bell@abc.com\",\"email1\":\"belly@abc.com\",\"age\":38}" -H "Content-Type: application/json" http://localhost:8080/customer/add </br>

DELETE: </br>
curl -X DELETE http://localhost:8080/customer/delete/3 </br>

Ex på record </br>
----------------- </br>
{"firstName":"Graham","lastName":"Bell","email":"graham.bell@abc.com","email1":"belly@abc.com","age":38}
{"firstName":"Thomas","lastName":"Edison","email":"thomas.edison@abc.com","email1":"Eddy@abc.com","age":33}