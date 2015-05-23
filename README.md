# webapp-skeleton
Skeleton for web applications using java server

## Tools:

- Maven
- Javax Servlet 3.0
- Jersey
- Jetty
- Jackson
- JDBI
- Thymeleaf
- SQlite (for the moment)

## Paths

hostname: {host}:{port}

### REST

application path: /

#### /users

- GET		/			return all users
- GET		/login		return the user with the same login if exists
- POST		/			create a user if he doesn't already exist
- POST		/login		same as GET /login with password encryption
- PUT		/login		modify a user if exists
- DELETE	/login		delete a user if exists

example: http://localhost:8080/rest/users/LordRski
