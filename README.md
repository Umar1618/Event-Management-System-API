# Event Management System
## Prerequisites:
1. Java Development Kit (JDK) installed on your machine (version 8 or higher).
2. Apache Maven installed on your machine.
3. MySQL database server installed and running.

## Setup Instructions:
1. Clone the project repository from GitHub
    1. git clone https://github.com/Umar1618/Event-Management-System-API.git
2. Navigate to the project directory:
    1. cd event-management-system
3. Open the application.properties file located in src/main/resources directory.
4. Update the database connection details (URL, username, password) according to your MySQL setup.
5. Setup base-URL for external APIs using property `api.base.url`
6. setup endpoints for external APIs using properties   `distance.calculator.url` and `weather.calculator.url`

## Accessing Swagger UI:
1. Open a web browser and go to the following URL
    1. http://localhost:8080/swagger-ui.html
2. Swagger UI will display the API documentation with details about available endpoints and request/response formats.

## Testing the API:
1. Use Postman or any REST client to send requests to the API endpoints.
2. Explore the different endpoints documented in Swagger UI and test their functionalities.
