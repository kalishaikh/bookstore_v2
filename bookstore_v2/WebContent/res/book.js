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

function showReview(){
	document.getElementById('reviewShow').style = "display: none;";
	document.getElementById('review').style = "display: block;";
}

function hideReview(){
	document.getElementById('review').style = "display: none;";
	document.getElementById('reviewShow').style = "display: block;";
}