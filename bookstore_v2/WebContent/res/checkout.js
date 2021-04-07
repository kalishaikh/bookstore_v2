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