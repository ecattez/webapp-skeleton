<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
	<head>
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script src='js/jquery-2.1.3.js'></script>
		<script src='js/main.js'></script>
	</head> 
	<body>
		<%-- On récupère le paramètre "name" pour afficher le nom de la personne qui vient sur la page --%>
		<%-- On n'affiche rien si le paramètre n'a pas été précisé --%>
		<h1>Welcome <%= request.getParameter("name") != null ? request.getParameter("name") : "" %> to my first web application :D</h1>
		
		<div>
			<h2>Connexion</h2>
			<form id="login">
				<input type="text" placeHolder="Login" />
				<input type="password" placeHolder="Password" />
				<input type="submit" value="Connexion" />
			</form>
		</div>
		
		<div>
			<h2>Inscription</h2>
			<form id="subscribe">
				<input type="text" placeHolder="Login" />
				<input type="password" placeHolder="Password" />
				<input type="text" placeHolder="Prénom" />
				<input type="text" placeHolder="Nom" />
				<input type="text" placeHolder="Date de naissance" />
				<input type="email" placeHolder="E-mail" />
				<input type="submit" value="Inscription" />
			</form>
		</div>
	</body>
</html>
