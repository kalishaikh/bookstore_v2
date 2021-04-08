$(document).ready(function () {
		
		var list1 = document.getElementById('country');
		
	
		list1.options[0] = new Option('Canada', 'Canada');
		list1.options[1] = new Option('US', 'US');
		
		$(".checkout").click(function (e) {
			var validated = test();

   				
  			 if (!validated) {
    			 e.preventDefault();
     			 return false;
  			 }
		}); 
	});
	
function test(){
	var justChar = /^[\sa-zA-Z]/;
	var charNumSpace = /^[\sa-zA-Z0-9]*$/;
	var charNum = /^[a-zA-Z0-9]*$/;
	var justNum = /^[0-9]*$/;
	
	
	var ok = true;


	if (document.getElementById("firstName").value == "" || !document.getElementById("firstName").value.match(justChar)) {
		document.getElementById("firstName").className = "form-control is-invalid";
		ok = false;
	} else {
		document.getElementById("firstName").className = "form-control is-valid";
	}
	
	if (document.getElementById("lastName").value == "" || !document.getElementById("lastName").value.match(justChar)) {
		document.getElementById("lastName").className = "form-control is-invalid";
		ok = false;
	} else {
		document.getElementById("lastName").className = "form-control is-valid";
	}
	
	if (document.getElementById("address").value == "" || !document.getElementById("address").value.match(charNumSpace)) {
		document.getElementById("address").className = "form-control is-invalid";
		ok = false;
	} else {
		document.getElementById("address").className = "form-control is-valid";	
	}
	
	if (document.getElementById("country").value == "") {
		document.getElementById("country").className = "form-control is-invalid";
		ok = false;
	} else {
		document.getElementById("country").className = "form-control is-valid";		
	}
	
	if (document.getElementById("province").value == "") {
		document.getElementById("province").className = "form-control is-invalid";
		ok = false;
	} else {
		document.getElementById("province").className = "form-control is-valid";
	}
	
	if (document.getElementById("zip").value == "" || !document.getElementById("zip").value.match(charNumSpace)) {
		document.getElementById("zip").className = "form-control is-invalid";
		ok = false;
	} else {
		document.getElementById("zip").className = "form-control is-valid";
	}
	
	if (document.getElementById("cc-name").value == "" || !document.getElementById("cc-name").value.match(justChar)) {
		document.getElementById("cc-name").className = "form-control is-invalid";
		ok = false;
	} else {
		document.getElementById("cc-name").className = "form-control is-valid";	
	}

	if (document.getElementById("cc-number").value == "" || !document.getElementById("cc-number").value.match(justNum)) {
		document.getElementById("cc-number").className = "form-control is-invalid";
		ok = false;
	} else {
		document.getElementById("cc-number").className = "form-control is-valid";		
	}
	
	if (document.getElementById("cc-cvv").value == "" || !document.getElementById("cc-cvv").value.match(justNum)) {
		document.getElementById("cc-cvv").className = "form-control is-invalid";
		ok = false;
	} else {
		document.getElementById("cc-cvv").className = "form-control is-valid";		
	}
	
	return ok;
	
}

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
function getStates(){
			var list1 = document.getElementById('country');
			var list2 = document.getElementById('province');
			var list1SelectedValue = list1.options[list1.selectedIndex].value;
			
			if (list1SelectedValue=='Canada')
            {
				
				list2.options.length=0;
				list2.options[0] = new Option('ON', 'ON');
				list2.options[1] = new Option('AB', 'AB');
				list2.options[2] = new Option('MN', 'MN');
				list2.options[3] = new Option('QC', 'QC');
				list2.options[4] = new Option('BC', 'BC');
				list2.options[5] = new Option('YK', 'YK');
				
			}
			else if (list1SelectedValue=='US')
            {
				
				list2.options.length=0;
				list2.options[0] = new Option('TX', 'TX');
				list2.options[1] = new Option('CA', 'CA');
				list2.options[2] = new Option('AR', 'AR');
				list2.options[3] = new Option('AK', 'AK');
				list2.options[4] = new Option('CO', 'CO');
				list2.options[5] = new Option('FL', 'FL');
				
			}
		
}




function getFoodItem(){
			var list1 = document.getElementById('country');
			var list2 = document.getElementById('province');
			var list1SelectedValue = list1.options[list1.selectedIndex].value;
			
			if (list1SelectedValue=='Canada')
            {
				
				list2.options.length=0;
				list2.options[0] = new Option('--Select--', '');
				list2.options[1] = new Option('Burger', 'Burger');
				list2.options[2] = new Option('Pizza', 'Pizza');
				list2.options[3] = new Option('Hotdog', 'Hotdog');
				list2.options[4] = new Option('Potato Chips', 'Potato Chips');
				list2.options[5] = new Option('French Fries', 'French Fries');
				
			}
			else if (list1SelectedValue=='US')
            {
				
				list2.options.length=0;
				list2.options[0] = new Option('--Select--', '');
				list2.options[1] = new Option('Coca Cola', 'Coca Cola');
				list2.options[2] = new Option('7up', '7up');
				list2.options[3] = new Option('Pepsi', 'Pepsi');
				list2.options[4] = new Option('Coffee', 'Coffee');
				list2.options[5] = new Option('Tea', 'Tea');
				
			}
}