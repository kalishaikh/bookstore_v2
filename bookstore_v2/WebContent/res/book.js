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

/**
 * Show and Hide the review form
 */
function showReview(){
	document.getElementById('reviewShow').style = "display: none;";
	document.getElementById('review').style = "display: block;";
}

function hideReview(){
	document.getElementById('review').style = "display: none;";
	document.getElementById('reviewShow').style = "display: block;";
}

function isLoggedIn(){
	var p = document.getElementById("fname").value;
	
	if(p === null || p === ""){
		alert("Please login to write a review!");
	}
	else{
		showReview();
	}
}

/**
 * Add to the cart
 */
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