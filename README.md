# Ticket Service App #

A Ticket Service rest api to reserve and hold tickets based on ticket storage

### Installation dependencies ###

The following dependencies are necessary: 

 - Java 7
 - maven 3


### Building and starting the server ###

To build the project run the following command on the root folder:

    mvn clean install 


#### REST API ####

##### Ticket Service #####

Url           |Verb          | Description
--------------|------------- | -------------
/getSeatsAvailable |GET          | retrieves number of seats available based on level.
/holdSeats |GET          | hold seats based on parameters passed.
/reserveSeats |GET          | reserve seats and returns confirmation code.


The project can be accessed via this URL:

   	https://localhost:8080/
	http://localhost:8080/TicketService/getSeatsAvailable?levelId=1
	http://localhost:8080/TicketService/holdSeats?seats=6&minLevel=1&maxLevel=3&email=test@ticket.com
	http://localhost:8080/TicketService/reserveSeats?email=test@ticket.com
	
