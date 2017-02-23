JsonJoin

A java test made for Cirro.

Execution instructions:
  Download dependencies:
    mvn clean install -Dmaven.test.skip
  Run program
    mvn exec:java -Dexec.mainClass="Main"

Unit Tests:
   In order to execute this test you are required to specify the paths for the j1.json and j2.json files in the file1 and file2 entries of the paths.properties file respectively, this properties file is found under: src/main/resources/

  mvn clean install -Dtest=TestResult
