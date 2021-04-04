/**
 * Provides the login logic for the login and register page
 */

/**
 * Validate a user email
 */


function isValid() {
	
	
	//Define variables
	var validEmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var userEmail = document.getElementById("email").value;
	var fname = document.getElementById("fname").value;
	var lname = document.getElementById("lname").value;
	var pass = document.getElementById("password").value;
	var valid = true;
	
	//On click make sure that all classes go back to neutral
	document.getElementById("email").className = "form-control";
	document.getElementById("fname").className = "form-control";
	document.getElementById("lname").className = "form-control";
	
	if (!userEmail.match(validEmail)){
		document.getElementById("email").className = "form-control is-invalid";
		alert("email invalid");
		valid = false;
	}

	else if(!fname || fname.length == 0){
		document.getElementById("fname").className = "form-control is-invalid";
		alert("fname too short");
		valid = false;
	}

	else if(!lname || lname.length == 0){
		document.getElementById("lname").className = "form-control is-invalid";
		alert("lname too short");
		valid = false;
		
	}

	else if(!pass || pass.length < 6){
		document.getElementById("password").className = "form-control is-invalid";
		alert("Password must be 6 characters long");
		valid = false;
	}
	
	
	return valid;	
}

/**
 * Send data to the server so that a user can be registered
 */

function registerUser() {
	
	if (isValid()){
		
		var request = new XMLHttpRequest();
		var fname = document.getElementById("fname").value;
		var lname = document.getElementById("lname").value;
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
		var address = "auth_ctrl/register"
		var data='';
		data += "&fname=";
		data += fname;
		data += "&lname=";
		data += lname;
		data += "&email=";
		data += email;
		data += "&password=";
		data += password;
		
		request.open("GET", (address + "?" + data), true);
		
		request.onreadystatechange = function() {

		if ((this.readyState == 4) && (this.status == 200)){
			
			if (request.responseText == "100"){
				alert("Email already registered!");
				return false;
			}
			
			else {
				alert("Registration successful...redirecting...");
				window.location.replace("main_page.jspx");
			}	
		}
		 
		};
		request.send(null);
		return false;
	}
	
	else {
		alert("Validation failed");
		return false;
	}
	
}

//Use Jquery to send data (seeing which method is easier)
$( document ).ready(function() {
	
	$("#login").submit(function (event){
		alert("login was clicked");
		event.preventDefault();
		$("#email").attr('class', 'form-control');
		$("#password").attr('class', 'form-control');
		
		$.post("auth_ctrl/login", {email : $("#email").val(), pass : $("#password").val()}, function(data, status){
			
			//Error Code 100 relates to an invalid e-mail
			if(data == "100"){
				$("#email").attr('class', 'form-control is-invalid');
				alert("E-mail does not exist in the database.");
			}
			
			//Error Code 200 relates to an invalid password
			else if(data == "200"){
				$("#password").attr('class', 'form-control is-invalid');
				alert("Password is invalid");
			}
			
			//Successful Login
			else {
				alert("User Authenticated, Welcome Back : " + data);
				window.location.replace("main_page.jspx");
			}
		});
	});
	
});




	
