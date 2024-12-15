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
                 				    var marca = $(this).val();
                 					selecionaModelo("https://fipe.parallelum.com.br/api/v2/cars/brands", marca);
                  	             $(this).removeAttr("selected");
                  	            }
                  	          });
                  	        });
                  	   $("#brand").find("option:selected").attr("selected", true);
                  	})
 	  		}
 	  	});



 function selecionaModelo(url, marca){

 	 var url = url+"/"+marca+"/models";

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
 								var modelo = $(this).val();
 								selecionaAno(url, modelo);
  	            	 $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	       $("#model").find("option:selected").attr("selected", true);
  		})
 }

 function selecionaAno(url, modelo){

 	var url = url+"/"+modelo+"/years";
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
  	             var ano = $(this).val();  								//if (modelo!=0){
                selecionaCombustivel(url, ano);
  	            $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	      $("#year").find("option:selected").attr("selected", true);
  	})

}

    function selecionaCombustivel(url, ano){

     	var url = url+"/"+ano;

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

