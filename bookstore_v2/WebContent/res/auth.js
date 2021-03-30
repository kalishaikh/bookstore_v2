/**
 * Provides the login logic for the login and register page
 */

/**
 * Validate a user email
 */

function isValid() {
	
	var validEmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var userEmail = document.getElementById("email").value;
	
	if (userEmail.match(validEmail)){
		alert("Valid Email!");
		return true;
	}
	
		alert("Invalid Email!");
		return false;	
}

/**
 * Send data to the server so that a user can be registered
 */

function registerUser() {
	
	if (isValid() == true){
		
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
			window.location.href("main_page.jspx");
		}
		 
	};
	 
	request.send(null);
	alert("User has been registered...redirecting to the main page'");
	return true;
	 }
	
	else return false;
		
	}
	
