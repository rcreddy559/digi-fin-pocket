---
description: Add Jakarta Validation (spring-boot-starter-validation) to the expense-service Maven project
---

1. Open the `pom.xml` file located at `expense-service/pom.xml`.
2. Locate the `<dependencies>` section.
3. Insert the following dependency block inside `<dependencies>`:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-validation</artifactId>
   </dependency>
   ```
   // turbo
4. Save the file.
5. Open a terminal in the project root (`c:\Users\SG0706221\GitHub\my-apps\digi-fin-pocket`).
6. Run the Maven command to download the new dependencies and compile the project:
   ```
   mvn clean compile
   ```
   // turbo
7. Verify that the import error is resolved by reopening the Java file that uses `jakarta.validation` imports.
