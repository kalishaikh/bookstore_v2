/**
 * Ensure that the form does not submit with an empty string
 */
function validateSearch(){
	var ok = true;
	var p = document.forms["mySearch"]["search"].value;
	
	if (p == null || p == "") {
  
        return false;
    }
		
	return ok;
}

function addToCart(address,bid,price,title,author,category) {
	var request = new XMLHttpRequest();
	var data = "";
	data = "&" + "bid=" + bid
	+ "&" + "price=" + price
	+ "&" + "title=" + title
	+ "&" + "author=" + author
	+ "&" + "category=" + category;
	
	request.open("POST", (address + data), true);
	request.send(null);

}