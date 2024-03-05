# System Documentation

This document provides an overview of the underlying operations behind the event list retrieval usecase.

## Get all events ( /getEvents )
> ### In case of Cached value
> - [TICKET - SERVICE] - After receiving a GET request to the API endpoint the service class validates the User Token
> - [TICKET - SERVICE] - If the value requested is already cached, the Ticket Service returns the cached values to the client.<br>

> ### In case of not cached value 
> - [TICKET - SERVICE] - After receiving a GET request to the API endpoint the service class validates the User Token
> - [TICKET - SERVICE] - If the value requested is not cached, it sends a GET request to the Partner module's `/getEvent` endpoint.
> - [PARTNER - MODULE] - The Partner module verifies the api key and api secret from the received request.
> - [PARTNER - MODULE] - The Partner module retrieves the list of events from its internal database
> - [PARTNER - MODULE] - The Partner module returns the list of events.
> - [TICKET - SERVICE] - The Ticket service caches the returned value ( if it is not an error message ).
> - [TICKET - SERVICE] - Returns the list of events

> Activity UML diagram
![UML-Activity-GetEvents.png](..%2Fuml%2FActivity%2FUML-Activity-GetEvents.png)

> Sequence UML diagram with modoule distinction
![UML-Sequence-getEvents.png](..%2Fuml%2FSequence%2FUML-Sequence-getEvents.png)