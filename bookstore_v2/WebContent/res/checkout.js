// Not in use right now 

function checkoutAjax(address) {
	var request = new XMLHttpRequest();
	var data = "";
	data = "&" + "firstName=" + document.getElementById("firstName").value
	+ "&" + "lastName=" + document.getElementById("lastName").value
	+ "&" + "email=" + document.getElementById("email").value
	+ "&" + "address=" + document.getElementById("address").value
	+ "&" + "country=" + document.getElementById("country").value
	+ "&" + "province=" + document.getElementById("province").value
	+ "&" + "zip=" + document.getElementById("zip").value
	+ "&" + "phone=" + document.getElementById("phone").value
	

	request.open("POST", (address + data), true);
	request.send(null);

}

