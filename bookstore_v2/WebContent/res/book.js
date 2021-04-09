/**
 * Ensure that the form does not submit with an empty string
 */
function validateSearch(){
	var ok = true;
	var p = document.forms["mySearch"]["search"].value;
	
	if (p === null || p === "") {
  
        return false;
    }
		
	return ok;
}

/**
 * Ensure ISBN is filled in 
 */
function validateISBN(){
	var ok = true;
	var p = document.forms["getBookForm"]["isbn"].value;
	
	if (p === null || p === "") {
  		
		alert("Nothing entered. Please enter an ISBN!");
        return false;
    }
		
	return ok;
}


/**
 * Ensure order ID is filled in 
 */
function validatePID(){
	var ok = true;
	var p = document.forms["getOrderForm"]["pid"].value;
	
	if (p === null || p === "") {
  		
		alert("Nothing entered. Please enter an Order ID!");
        return false;
    }
		
	return ok;
}


/**
 * Ensure that the Review is complete
 */
function validateReview(){
	var ok = true;
	var p = document.forms["myReview"]["reviewTitle"].value;
	
	if (p === null || p === "") {
  		alert("Not Submitted! Please enter a title!");
		document.getElementById("rTitle-error").style.display = "inline";
		document.getElementById("rTitle-error").style.color = "red";
		ok = false;
	} else{
		document.getElementById("rTitle-error").style.display = "none";
	}
	
	if(!ok)
		return false;
		
	p  = $("input[type='radio'][name='rate']:checked").val();
	
	if (p === null || p === "" || p === undefined) {
		alert("Not Submitted! Please rate the book!");
		document.getElementById("rate-error").style.display = "inline";
		document.getElementById("rate-error").style.color = "red";
		ok = false;
	} else{
		document.getElementById("rate-error").style.display = "none";
	}
	
	if(!ok)
		return false;
	
	p = document.getElementById("reviewContent").value;
	if (p === null || p === "" ||  p === "Write your review here...") {
		alert("Not Submitted! Please enter a your review!");
		document.getElementById("content-error").style.display = "inline";
		document.getElementById("content-error").style.color = "red";
		ok = false;
	} else{
		document.getElementById("content-error").style.display = "none";
	}
	
	if(!ok)
		return false;
		
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
	
	alert(title + " added to cart!");
	
	
	request.open("POST", (address + data), true);
	request.send(null);
}

var cartChanges = 0;

function incrementCartCount(quantity) {
	target = document.getElementById("cart");
	cartChanges = cartChanges + 1;
	target.innerHTML = ("Shopping Cart (" + (quantity + cartChanges) + ")");
}


/**
 * Add review
 */
function addReview(address,bid) {
	var request = new XMLHttpRequest();
	var data = "";
	if(validateReview()){
		
		data = "&" + "bid=" + bid
		+ "&" + "name=" + document.getElementById("fname").value
		+ "&" + "reviewTitle=" + document.getElementById("reviewTitle").value
		+ "&" + "rate=" +  $("input[type='radio'][name='rate']:checked").val()
		+ "&" + "reviewContent=" + document.getElementById("reviewContent").value;
		
		request.open("POST", (address + data), true);
		request.onreadystatechange = function(){
			handlerReview(request);
		};
		request.send(null);
	}
}

function handlerReview(request){
	if ((request.readyState == 4) && (request.status = 200)){
		if(request.responseText === "true"){
			alert("Review has been submitted.");
			hideReview();
		}else{
			alert("Error! Review has not been submitted. Please try again later.");
			hideReview();
		}
	}
}

