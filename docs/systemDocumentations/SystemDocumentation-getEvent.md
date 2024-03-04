# System Documentation

This document provides an overview of the underlying operations behind the use cases of the system.

## Get a specific event ( /getEvents )

> - [TICKET - SERVICE] - After receiving a GET request to the API endpoint the service class validates the User Token
> - [TICKET - SERVICE] - The ticket service sends a HTTP GET Request to the Partner module's `/getEvent` endpoint.
> - [PARTNER - MODULE] - The Partner module verifies the api key and api secret from the received request.
> - [PARTNER - MODULE] - The Partner module retrieves a specific event from its internal database.
> - [PARTNER - MODULE] - The Partner module assembles the necessary response depending on the `detailed` param's value.
> - [PARTNER - MODULE] - The Partner module returns the required object for the Ticket Service.
> - [TICKET - SERVICE] - The Ticket Service receives the response and forwards it to the client.

> Activity UML diagram
![UML-Activity-GetEvent.png](..%2Fuml%2FActivity%2FUML-Activity-GetEvent.png)

> Sequence UML diagram with modoule distinction
![UML-Sequence-getEvent.png](..%2Fuml%2FSequence%2FUML-Sequence-getEvent.png)