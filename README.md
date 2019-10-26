# report maker
Small java project (Spring-boot, H2 & JDBC) that takes 2 .csv files, loads them in a H2 (in-memory) database, queries information for a report and output it to a text file.

Developed on Windows 10 with IntelliJ

How to access the in memory H2 Database:
- Comment out the last line in the main() method
- Run the project from within the IDE.
- database is accessible at http://localhost:8080/h2-console
- jdbc url: jdbc:h2:mem:testdb | Username: sa | no password.
- individual sql queries can be tried (...\src\main\resources\workingQueries.sql)

How to Build:
- Import or clone this project in your IDE.
- In a command-line prompt navigate (cd) to: project-path...\reporting\
- build with the command: gradlew build
- resulting .jar file should be in: ...\reporting\build\libs\reporting-0.0.1-SNAPSHOT.jar

How to Run:
- Open a command prompt and navigate to the folder where your .jar file is saved (example: cd C:\Users\xyz\Desktop\test\ )
- Run the .jar file with the command: java -jar ...\absolute-path-to-the-jar\reporting-0.0.1-SNAPSHOT.jar

Known Limitations: 
- Structure of the project needs be improved. Usage of Spring boot when it is not a micro-service.
- Issue: regular junit tests (such as in QueryTests) cannot be run. Need to learn how to write unit tests for spring boot.
- .csv file are included in the project.

