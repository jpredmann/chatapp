## Dependencies

Java 1.8
Maven 3
MySQL 5


## QuickStart

Create a MySQL database for the chat application to use.
The app is expecting the DB name to be 'chatapp'.

In the application.properties file set the DB username and password

Build with ```mvn package```
Start the API with ```mvn spring-boot:run```

## Endpoints

### Create Account

```POST /api/accounts```

In the body provide values for these fields:
- username
- password
- firstName
- lastName

### List Users

```GET /api/accounts```

No fields are required in the body

### Send Message

```POST /api/messages```

In the body provide values for these fields:

- sender (must match username in authentication header)
- reciever
- content (the text of the message)

### List User's InBound Messages

```GET /api/messages```

No fields are required in the body 

