/* main.js */

$(document).ready(function() {

	$("#login").on("submit", function(e) { login(e) });
	$("#subscribe").on("submit", function(e) { subscribe(e) });

});

function login(e) {
	var form = function(i) { return $("#login").find("input").get(i).value; }
	e.preventDefault(); // Annule l'action du formulaire (évite la redirection ou le raffraichissement de la page)
	$.ajax({
		type: "POST",
		dataType: "json",
		url: "/rest/usersdb/" + form(0),
		data: { "password" : form(1) },
		success: function(data, textStatus, jqXHR) {
			store(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Login error");
		}
	});
}

function subscribe(e) {
	var form = function(i) { return $("#subscribe").find("input").get(i).value; }
	e.preventDefault();
	$.ajax({
		type: "POST",
		url: "/rest/usersdb/",
		data: { "login" : form(0), "password" : form(1), "firstname" : form(2), "lastname" : form(3), "birthday" : form(4), "email" : form(5) },
		success: function(data, textStatus, jqXHR) {
			alert("Suscribe success");
			$("#subscribe").find("input[type!=submit]").val("")
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Suscribe error");
		}
	});
}

function store(data) {
	// On crée un formulaire le temps de l'envoie des données
	var form = "<form id='sendto' action='index.jsp' method='post'><input name='user' type='text' value='" + data.login + "'><input type='submit'></form>";
	document.body.innerHTML = form;
	$("#sendto").submit();
	console.log("stored");
}
