# API REFERENCE

## OpenAPI / Swagger UI Documentation

This section provides instructions on how to access the OpenAPI and Swagger UI documentation for the various modules in both modules.

### Ticket Service Module

To access the OpenAPI / Swagger UI documentation for the Ticket Service Module, follow these steps:

1. Ensure that the Ticket Service Module is running on your local machine.
2. Open your web browser and navigate to the following URL:
   ```
   http://localhost:8082/ticket-api-docs-ui.html
   ```
3. You should now see the Swagger UI interface displaying the documentation for the Ticket Service API.

### Partner Module

To access the OpenAPI / Swagger UI documentation for the Partner Module, follow these steps:

1. Ensure that the Partner Module is running on your local machine.
2. Open your web browser and navigate to the following URL:
   ```
   http://localhost:8081/partner-api-docs-ui.html
   ```
3. You should now see the Swagger UI interface displaying the documentation for the Partner Module API.


## Exercise endpoints via Postman
### You can use the pre-made collections to test module functionalities on this link:
> [Go to postman collection!](https://www.postman.com/erikeei/workspace/otp-ticket-service/folder/33255309-fdc9264a-a770-4ab6-8085-d7dcdb72efbd)

### Or, you can get the pre-configured collections for both modules here:
> - Partner : [Partner Module Postman collection](postmanCollections%2FPartner%20Module.postman_collection.json)<br>
> - Ticket Service : [Ticket Service Module Postman collection](postmanCollections%2FTicket%20Service%20Module.postman_collection.json)

### General information about testing with postman
> - Every request has tests included, so it can automatically verify the correctness of the received responses
> - Every request has pre-configured example values, the only interaction from the user needed is to send the requests
> - For testing reservation operations on partner module, the pre-configured event and seat is valid.<br> The sequence of testing the functionality seamlessly is the following: 
> > **`[ Reserve -> Review -> Delete ]`** <br>
> - The received reservation id after the `Reserve` step is extracted to a collection variable every time, so it can be used in the `Review` and `Delete` steps.Therefore no parameter modifications needed to repeat the sequence.

To exercise the endpoint via Postman, follow these steps:

1. **Download and Install Postman:** If you don't have postman desktop agent, download and install Postman from [here](https://www.postman.com/downloads/).
   ( If you are using the website, you do not have to do steps 2 )
2. **Import Postman Collections:**
   - Download the provided Postman collections for the Ticket Service Module and the Partner Module.
   - Open Postman and import these collections. To import, click on the "Import" button in the top left corner of the Postman window, select the downloaded JSON files, and then click "Open".

3. **Navigate to Collections:**
   - In Postman, locate the collections sidebar on the left-hand side.
   - Choose the appropriate collection based on the module you want to test:
      - For the Ticket Service Module, select the imported collection related to the Ticket Service.
      - For the Partner Module, select the imported collection related to the Partner Service.

4. **Select Request:**
   - Within the chosen collection, browse through the list of requests to find the one corresponding to the specific endpoint you want to exercise.

5. **Ensure Module is Running:**
   - Before sending the request, ensure that the module corresponding to the endpoint you are testing is running on your local network.

6. **Send Request:**
   - Click on the request you want to execute.
   - Click the "Send" button to execute the request.

7. **Review Response:**
   - After sending the request, review the response returned by the API to verify its correctness.

8. **Check Test Results:**
   - Postman will automatically run any tests associated with the request.
   - Check the test results to ensure that the API behaves as expected, including handling custom exceptions.

9. **Repeat:**
   - Repeat the process for any other endpoints you wish to exercise within the chosen module.

By following these steps, you can effectively exercise the endpoints of the Ticket Service Module and the Partner Module using Postman.

