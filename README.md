#  MAN Challenge <!-- omit in toc -->

#### A simple kitchen system, that receives and fetches orders. Developed for MAN hiring process.

---

# Prerequisites <!-- omit in toc -->
- [JDK11](https://openjdk.java.net/install/)
- [Maven](https://maven.apache.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Insomnia](https://insomnia.rest/) or any other API testing tool

---

# Table of Contents <!-- omit in toc -->
- [Installation](#installation)
- [Execution and Testing](#execution-and-testing)
- [Routes](#routes)
- [Next steps](#next-steps)

# Installation
1. Clone the repository:
    ```
    git clone https://github.com/thiagotandrade/man-challenge.git
    ```
2. Open Intellij.  
3. navigate to `File -> Open` and select the man-challenge project folder.
4. Intellij will start to build resources and the project will be ready to run.

# Execution
* To run the program, open the Project Explorer windows and navigate to `src/main/java` and right-click on the `ManChallengeApplication` and select Run.

* The exposed port is 8081. If you'd like to modify it, simply open the `application.properties` and change the property `server.port` to the desired port.

* To test the application, you can either use an API testing tool, such as Insomnia and Postman. The other way is to execute the integration and unit tests. To do so, on the Project window, right-click the package `eu.man.challenge` from **src/test/java**.

# Routes
1. **GET** `/order/all`: Returns all orders currently registered.
2. **GET** `/order/{id}`: Passing an ID as a Path parameter, return the order matching the ID.
3. **POST** `/order`: Create a new order. The order is an JSON object with a customer name and a list of ingredients. It has the following format:
 ```json
{
    "customer": "customer-name",
    "ingredients": [
        "ingredient-1",
        "ingredient-2",
        "ingredient-3"
    ]
}
   ```

# Next steps

1. Add deletion feature.
2. Create deletion feature unit and integration tests.
3. Replace junit assertAll with a shorter version using assertj.
4. Better organize test suites.
5. Achieve 100% coverage on all modules (currently 94% of lines are covered).