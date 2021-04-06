$( document ).ready(function() {
	
	/**Detect which dropdown menu has been click*/
	
		/**Create a post command with type 0 if Best Selling Books is Selected */
		$("#bestSellingBooks").click(function() {
				$.post("auth_ctrl/analytics", {type:"0"}, function(data, success){
						$("body").append("<h1 class ='text-center'>Displaying Top 10 Books Sold</h1>")
						$("body").append(data);
					});
		});
		
		$("#bestSellingGenre").click(function(){
				$.post("auth_ctrl/analytics", {type:"1"}, function(data, success){
						$("body").append("<h1 class ='text-center'>Displaying Top 10 Genres</h1>")
						$("body").append(data);
					});
		});
		
		$(document).on("click", ".dropdown-item", function(){
			$("h1").remove();
			$(".table").remove();
	});
});