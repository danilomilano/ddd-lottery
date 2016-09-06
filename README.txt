DDD Lottery System API
----------------------

Lottery system using Domain Driven Design (DDD), providing a REST API to create Tickets in a Lottery.

To build the project and execute tests:

    mvn clean install 

To construct the project in eclipse:

    mvn eclipse:eclipse

Tests
-----
To execute tests outside the build cycle process:

    mvn test

By default is executed:

- Domain tests
- EJB tests: using Embedded TomEE + openEJB
- REST API tests: connecting to http://localhot:8080/ddd-lottery/rest/tickets (to execute these tests is necessary upload the war in a local server in the port 8080 - validated with Wildfly 10)
