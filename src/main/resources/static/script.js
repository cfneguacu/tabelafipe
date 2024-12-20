 	   $.ajax({
 	  		method: "GET",
 	  		url: "https://fipe.parallelum.com.br/api/v2/cars/brands",
 	  		success: function(response){
 	  			$('#brand > option').remove();
 	  			for (var i = 0; i < response.length; i++){
 	 				$('#brand').append('<option value="'+response[i].code+'">'+response[i].name+'</option>');
 	 			}
 	 			$("#brand").click(function () {
                  	        $("#brand").each(function () {
                  	          $(this).find("option").each(function () {
                  	            if ($(this).attr("selected")) {
                 				    var brand = $(this).val();
                 					getModel("https://fipe.parallelum.com.br/api/v2/cars/brands", brand);
                  	             $(this).removeAttr("selected");
                  	            }
                  	          });
                  	        });
                  	   $("#brand").find("option:selected").attr("selected", true);
                  	})
 	  		}
 	  	});



 function getModel(url, brand){

 	 var url = url+"/"+brand+"/models";

 		$.ajax({
 	  				method: "GET",
 	  				url: url,
 	  				success: function(response){
 	  				$('#model > option').remove();
 	  				for (var i = 0; i < response.length; i++){
 	 				$('#model').append('<option value="'+response[i].code+'">'+response[i].name+'</option>');
 	 				}
 	  			}

 	  			});

 				$("#model").click(function () {
  	        		$("#model").each(function () {
  	         			 $(this).find("option").each(function () {
  	            			if ($(this).attr("selected")) {
 								var model = $(this).val();
 								getYear(url, model);
  	            	 $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	       $("#model").find("option:selected").attr("selected", true);
  		})
 }

 function getYear(url, model){

 	var url = url+"/"+model+"/years";
 	//}

 	$.ajax({
 	  		method: "GET",
 	  		url: url,
 	  		success: function(response){
 	  			$('#year > option').remove();
 	  			for (var i = 0; i < response.length; i++){
 	 				$('#year').append('<option value="'+response[i].code+'">'+response[i].code+'</option>');
 	 			}
 	  		}
 	  	});

 	$("#year").click(function () {
  	        $("#year").each(function () {
  	          $(this).find("option").each(function () {
  	           if ($(this).attr("selected")) {
  	             var year = $(this).val();  								//if (modelo!=0){
                 getFuel(url, year);
  	            $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	      $("#year").find("option:selected").attr("selected", true);
  	})

}

    function getFuel(url, year){

     	var url = url+"/"+year;

     	$.ajax({
     	  		method: "GET",
     	  		url: url,
     	  		success: function(response){
     	  			$('#fuel > option').remove();
     	 				$('#fuel').append('<option value="'+response.fuel+'">'+response.fuel+'</option>');
     	  		}
     	  	});

     	$("#fuel").click(function () {
      	        $("#fuel").each(function () {
      	          $(this).find("option").each(function () {
      	           if ($(this).attr("selected")) {

      	            $(this).removeAttr("selected");
      	            }
      	          });
      	        });
      	      $("#fuel").find("option:selected").attr("selected", true);
      	})

   }

