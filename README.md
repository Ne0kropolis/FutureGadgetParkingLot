#**Future Gadget Parking Lot**

The Future Gadget Parking Lot Application is a web-based application which provides the ability to create, retrieve, and rate parking lot tickets. The current version provides support for
the tracking of multiple parking lots and pricing schemes.

Future Gadget Parking Lot is a Maven based Project utilising the Java Spring framework with Resteasy Jax-rs services which consume and produce JSON payloads.

##Getting Started

First, clone the Future Gadget Parking Lot repository from Github to a location of your choice.

`$ git clone https://github.com/Ne0kropolis/FutureGadgetParkingLot`

next, using your preferred IDE or maven-enabled CLI run the following maven goal:

`mvn tomcat7:run`

verify the application is running by visiting

`http://localhost:8080/tickets/all`

##Builds & Goals

###Maven

The project can be built using typical maven build goals such as

`mvn clean compile package`

###Flyway

If using an external database for flyway integration the flyway:migrate goal can be run with the appropriate versioning files to build the base schema and insert dummy testing data

`mvn flyway:migrate`

In the event of a database corruption the flyway:clean goal can be used to undo an remaining version migration issues

`mvn flyway:clean`

###Tomcat

An embedded tomcat server can be run with the tomcat7:run goal

`mvn tomcat7:run`

###Automated Testing

Automated tests can be triggered with mvn:test

`mvn:test`

These include unit tests for the domain Entities, DAOs, Services, and Controllers.

###Codestyle Checks

Caodestyle checks can be run with

`mvn Checkstyle:Checkstyle`

and

`mvn pmd:check`

Checkstyle currently runs the Sun default codestyle checkstyle.xml schemas

#HttpRequests

This section contains a comprehensive list of the possible application URI's and requests.

##Pricing

###@GET

`http://localhost:8080/pricing/all`

retrieves a list of all pricing objects from the database

`http://localhost:8080/pricing/get/{id}`

retrieves a single pricing object with a given pricing Id.

`http://localhost:8080/pricing/scheme/{id}`

retrieves a list of all pricing objects with a given pricing scheme number.

###@POST

`http://localhost:8080/pricing/create/json`

creates a pricing object from a given json object
e.g



 	"pricingId": 1,
 	"pricingSchemeNumber": 1,
 	"duration": 10,
 	"granularity": "M",
 	"price": 10
 

creates a list of pricing objects from a given json.
 
`http://localhost:8080/pricing/create/list/json`

###@PUT

updates a pricing object from a given json.

`http://localhost:8080/pricing/update/json`

###@Delete

deletes a pricing object with a given id from the database.

`http://localhost:8080/pricing/delete/{id}`
