# System Documentation

This document provides an overview of the underlying operations behind the use cases of the system.

## Pay and make a reservation ( /pay )

> - [TICKET - SERVICE] - After receiving a POST request to the API endpoint the service class validates the User Token
> - [TICKET - SERVICE] - Sends a http event request with `detailed=true` param.
> - [PARTNER - MODULE] - The Partner module verifies the api key and api secret from the received request.
> - [PARTNER - MODULE] - The Partner module retrieves a specific event from its internal database.
> - [PARTNER - MODULE] - The Partner module assembles the necessary response depending on the `detailed` param's value.
> - [PARTNER - MODULE] - The Partner module returns the required object for the Ticket Service.
> - [TICKET - SERVICE] - The Ticket Service receives the event data and verifies, that the requested seat is reservable.
> - [TICKET - SERVICE] - After verification, the ticket service attempts to pay the reservation fee with the user's card.
> - [TICKET - SERVICE] - After successful payment, the ticket service sends a POST HTTP request to the Partner module's `/reserve`.
> - [PARTNER - MODULE] - The Partner module verifies the api key and api secret from the received request.
> - [PARTNER - MODULE] - The Partner module retrieves a specific event from its internal database.
> - [PARTNER - MODULE] - The Partner module verifies that the seat is reservable, and it matches the required event.
> - [PARTNER - MODULE] - The Partner module updates the selected seat's `reserved` field in the database.
> - [PARTNER - MODULE] - The Partner module saves the reservation into the database.
> - [PARTNER - MODULE] - The Partner module returns the reservation ID to the ticket service.
> - [TICKET - SERVICE] - The Ticket Service receives the response and forwards it to the client.

> Activity UML diagram
![UML-Activity-Pay.png](..%2Fuml%2FActivity%2FUML-Activity-Pay.png)

> Sequence UML diagram with modoule distinction
![UML-Sequence-pay.png](..%2Fuml%2FSequence%2FUML-Sequence-pay.png)