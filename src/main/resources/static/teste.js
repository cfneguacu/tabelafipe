
 	   $.ajax({
 	  		method: "GET",
 	  		url: "brands",
 	  		success: function(response){
 	  			$('#selecionarMarca > option').remove();
 	  			for (var i = 0; i < response.length; i++){
 	 				$('#selecionarMarca').append('<option value="'+response[i].code+'">'+response[i].name+'</option>');
 	 			}
 	  		}
 	  	});

 	$("#selecionarMarca").click(function () {
  	        $("#selecionarMarca").each(function () {
  	          $(this).find("option").each(function () {
  	            if ($(this).attr("selected")) {
 				    var marca = $(this).val()
 					selecionaModelo("brands", marca);
  	             $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	   $("#selecionarMarca").find("option:selected").attr("selected", true);
  	})

 function selecionaModelo(url, marca){

 	 var url = url+"/"+marca+"/models";

 		$.ajax({
 	  				method: "GET",
 	  				url: url,
 	  				success: function(response){
 	  				$('#selecionarModelo > option').remove();
 	  				for (var i = 0; i < response.length; i++){
 	 				$('#selecionarModelo').append('<option value="'+response[i].code+'">'+response[i].name+'</option>');
 	 				}
 	  			}

 	  			});

 				$("#selecionarModelo").click(function () {
  	        		$("#selecionarModelo").each(function () {
  	         			 $(this).find("option").each(function () {
  	            			if ($(this).attr("selected")) {
 								var modelo = $(this).val();
 								selecionaAno(url, modelo);
  	            	 $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	       $("#selecionarModelo").find("option:selected").attr("selected", true);
  		})
 }

 function selecionaAno(url, modelo){

 	var url = url+"/"+modelo+"/years";
 	//}

 	$.ajax({
 	  		method: "GET",
 	  		url: url,
 	  		success: function(response){
 	  			$('#selecionarAno > option').remove();
 	  			for (var i = 0; i < response.length; i++){
 	 				$('#selecionarAno').append('<option value="'+response[i].code+'">'+response[i].code+'</option>');
 	 			}
 	  		}
 	  	});

 	$("#selecionarAno").click(function () {
  	        $("#selecionarAno").each(function () {
  	          $(this).find("option").each(function () {
  	           if ($(this).attr("selected")) {
  	             var ano = $(this).val();  								//if (modelo!=0){
                selecionaCombustivel(url, ano);
  	            $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	      $("#selecionarAno").find("option:selected").attr("selected", true);
  	})

}

    function selecionaCombustivel(url, ano){

     	var url = url+"/"+ano;

     	$.ajax({
     	  		method: "GET",
     	  		url: url,
     	  		success: function(response){
     	  			$('#selecionarCombustivel > option').remove();
     	 				$('#selecionarCombustivel').append('<option value="'+response.fuel+'">'+response.fuel+'</option>');
     	  		}
     	  	});

     	$("#selecionarCombustivel").click(function () {
      	        $("#selecionarCombustivel").each(function () {
      	          $(this).find("option").each(function () {
      	           if ($(this).attr("selected")) {

      	            $(this).removeAttr("selected");
      	            }
      	          });
      	        });
      	      $("#selecionarCombustivel").find("option:selected").attr("selected", true);
      	})

   }

