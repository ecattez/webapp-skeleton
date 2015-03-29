# webapp-skeleton
Skeleton for web application with a java server.

## Tools:

- Maven
- Jersey
- Jetty
- Jackson
- JDBI
- SQlite (for the moment)

## Paths

hostname: {host}:{port}

application path: /rest

### /users or /usersdb (choice between a simple map or a database)

- GET		/			return all users
- GET		/login		return the user with the same login if exists
- POST		/			create a user if he doesn't already exist
- POST		/login		same as GET /login with password encryption
- PUT		/login		modify a user if exists
- DELETE	/login		delete a user if exists

example: http://localhost:8080/rest/usersdb/LordRski
