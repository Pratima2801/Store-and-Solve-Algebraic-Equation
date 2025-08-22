### Application Overview
This Spring Boot backend lets you store algebraic equations as postfix trees in-memory, then retrieve and evaluate them with variable substitutions through REST APIs.
It ensures correct parsing, tree-based storage, and robust error handling, fully testable via Postman.

### Setup Instructions

**Pre-requisities**
   1. Java 17+
   2. Maven installed
   3. Postman to test.
   
**Steps**
   1. Clone the project
         -> git clone https://github.om<your-username>/datasetapi.git
         -> cd equations

   2. Build project with Maven
         -> mvn clean install

   3. Run the application
         -> mvn spring-boot:run

   4. The API runs at http://localhost:8080/api/equations
   
   5. Test using Postman.  

**Steps to Test** 
   1. Store Equation
         --> POST /api/equations/store → Save a new equation in Postfix tree format.
   2. Retrieve Equation
         --> GET /api/equations → Get back all stored equations in human-readable form.      
   3. Evaluate Equation
         -->  POST /api/equations/{id}/evaluate → Calculate the result of a stored equation using given variable values.     