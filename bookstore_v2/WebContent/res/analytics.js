$( document ).ready(function() {
	
	/**Detect which dropdown menu has been click*/
	
		
		/**Create a post command with type 1 if Best Genre is Selected */
		$("#bestSellingGenre").click(function(){
				$.post("auth_ctrl/analytics", {type:"1"}, function(data, success){
						$("body").append("<h1 class ='text-center'>Displaying Top Genres By Books Sold</h1>")
						$("body").append(data);
					});
		});
		
		
		/**Create a post command with type 2 if Countries With Most Purchase is Selected */
		$("#mostVisits").click(function(){
				$.post("auth_ctrl/analytics", {type:"2"}, function(data, success){
						$("body").append("<h1 class ='text-center'>Displaying Countires With The Most Purchases</h1>")
						$("body").append(data);
					});
		});
		
		/**Create a post command with type 3 if Amount spent is selected */
		$("#totalSpent").click(function(){
				$.post("auth_ctrl/analytics", {type:"3"}, function(data, success){
						$("body").append("<h1 class ='text-center'>Displaying Most Spent By Users</h1>")
						$("body").append(data);
					});
		});
		
		/**Create a post command with type 4 if Favourite Books is selected */
		$("#favBook").click(function(){
				$.post("auth_ctrl/analytics", {type:"4"}, function(data, success){
						$("body").append("<h1 class ='text-center'>Displaying Top Rated Books By Users</h1>")
						$("body").append(data);
					});
		});
		
		/**Create a post command with type 5 if Favourite Books is selected */
		$("#bestSellingBooks").click(function(){
				$.post("auth_ctrl/analytics", {type:"5"}, function(data, success){
						$("body").append("<h1 class ='text-center'>Displaying Total Books Sold Since Going Live</h1>")
						$("body").append(data);
					});
		});
		
		$(document).on("click", ".dropdown-item", function(){
			$("h1").remove();
			$(".table").remove();
	});
});