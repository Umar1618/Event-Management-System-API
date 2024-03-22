# Event Management System
## Prerequisites:
1. Java Development Kit (JDK) installed on your machine (version 8 or higher).
2. Apache Maven installed on your machine.
3. MySQL database server installed and running.

### Setup Instructions:
1. Clone the project repository from GitHub
    1. git clone https://github.com/Umar1618/Event-Management-System-API.git
2. Navigate to the project directory:
    1. cd event-management-system
3. Open the application.properties file located in src/main/resources directory.
4. Update the database connection details (URL, username, password) according to your MySQL setup.
5. Setup base-URL for external APIs using property `api.base.url`
6. setup endpoints for external APIs using properties   `distance.calculator.url` and `weather.calculator.url`

### Accessing Swagger UI:
1. Open a web browser and go to the following URL
    1. http://localhost:8080/swagger-ui.html
2. Swagger UI will display the API documentation with details about available endpoints and request/response formats.

### Testing the API:
1. Use Postman or any REST client to send requests to the API endpoints.
2. Explore the different endpoints documented in Swagger UI and test their functionalities.


# Justification and Reflection:
Tech Stack and Database Selection Report

1. Choice of Tech Stack:

    Java: Java was chosen as the primary programming language due to its popularity, robustness, and extensive ecosystem of libraries and frameworks.
    Spring Boot: Spring Boot was selected for its ease of use in developing and deploying production-grade Spring-based applications. It provides out-of-the-box features for building RESTful APIs, managing configurations, and handling dependencies.
    Hibernate: Hibernate ORM was chosen for its powerful object-relational mapping capabilities, simplifying database interactions and providing portability across different database platforms.
    MySQL: MySQL was chosen as the relational database management system due to its widespread adoption, robustness, and scalability. It is well-suited for applications with relational data models.
    Swagger UI: Swagger UI was selected for its ability to automatically generate interactive API documentation, making it easier for developers to understand and consume the API endpoints.
    Postman: Postman was chosen as the API testing tool for its user-friendly interface, comprehensive features for testing APIs, and ability to automate API testing workflows.

2. Design Decisions:

    Spring Boot: Spring Boot's convention-over-configuration approach allowed for rapid development without the need for extensive boilerplate code. Dependency injection and auto-configuration simplified the setup and management of application components.
    Hibernate: Hibernate's object-relational mapping capabilities abstracted away the complexities of SQL queries, enabling developers to work with database entities using object-oriented paradigms.
    MySQL: MySQL was chosen for its compatibility with Hibernate ORM and its ability to scale horizontally and vertically to accommodate growing data needs.
    Swagger UI: Swagger UI was integrated into the application to provide developers with self-documenting APIs, reducing the need for manual documentation and improving API adoption and understanding.
    Postman: Postman was utilised for comprehensive API testing, including functional testing, performance testing, and automated testing. Its ability to organise and execute test suites streamlined the testing process.

3. Addressing Challenges:

    Integration with External APIs: Challenges were addressed by implementing robust error handling mechanisms to gracefully handle failures when calling external APIs. Asynchronous processing techniques, such as CompletableFuture or reactive programming, were used to minimise response times and ensure responsiveness.
    API Documentation: Swagger UI was integrated to automatically generate API documentation, reducing the overhead of maintaining separate documentation files. Clear and concise documentation annotations were added to controller methods to improve readability and understanding.
