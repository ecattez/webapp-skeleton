<html>
	<head>
		<meta charset="UTF-8" />
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script src='js/jquery-2.1.3.js'></script>
		<script src='js/main.js'></script>
	</head> 
	<body>
		<%-- On récupère le paramètre "name" pour afficher le nom de la personne qui vient sur la page --%>
		<%-- On n'affiche rien si le paramètre n'a pas été précisé --%>
		<h1>Welcome <%= request.getParameter("name") != null ? request.getParameter("name") : "" %> to my first web application :D</h1>
	</body>
</html>
