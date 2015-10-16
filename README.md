# webapp-skeleton
Skeleton for web applications using java server.
[Currently in development]

## Dependences:

- Maven
- Javax Servlet 3.0
- Jackson (REST)
- Jersey (REST)
- Jetty (SERVER)
- ORMLite (DAO)
- Thymeleaf (VIEW)
- SQlite (for the moment)

## Paths

main path: {host}:{port}

### REST

application path: {main}/

example: http://localhost:8080/users/ecattez

## DAO

Le pattern DAO a été pensé ici de façon "thématique".

Par exemple, si vous cherchez à obtenir des données relatives à l'objet Group, qu'importe l'objet que vous avez actuellement, la réponse se trouvera dans GroupDao.

De plus, et c'est IMPORTANT, l'application a été pensée pour mettre l'utilisateur au centre de l'application. L'utilisateur se doit donc d'avoir des informations minimales sur les objets qui lui sont attachés. Par exemple, un utilisateur appartient à une compagnie et un groupe. C'est pourquoi il a en sa possession le nom de sa compagnie et le nom de son groupe. UserDao permet donc d'accéder à l'objet Company et l'objet Group relatifs à l'utilisateur.

## SecurID

Utiliser l'annotation @Authenticated sur les méthodes qui nécessite d'être authentifié pour y accéder.
Le SecurID fournit un token à l'utilisateur.
