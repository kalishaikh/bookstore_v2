$( document ).ready(function() {
	
	/** Check for logout click
	 */
	$("#logout").click(function(){
		$.post('auth_ctrl/logout', {}, function(result) {
    	alert('You have successfully logged out!');
		});
	});
});

