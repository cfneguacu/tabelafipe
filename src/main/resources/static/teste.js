var url = "https://parallelum.com.br/fipe/api/v2/cars/brands";

 	   $.ajax({
 	  		method: "GET",
 	  		url: url,
 	  		success: function(response){
 	  		console.log(response)
 	  			$('#selecionarMarca > option').remove();
 	  			for (var i = 0; i < response.length; i++){
 					//marca = response[i].codigo
 	 				$('#selecionarMarca').append('<option value="'+response[i].code+'">'+response[i].name+'</option>');
 	 			}
 	  		}
 	  	});

 	$("#selecionarMarca").click(function () {
  	        $("#selecionarMarca").each(function () {
  	          $(this).find("option").each(function () {
  	            if ($(this).attr("selected")) {
 					//var comboMarca = document.getElementById("selecionarMarca");
 				     marca = $("#selecionarMarca option:selected").val()
 					selecionaModelo(url, marca);
  	             $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	   $("#selecionarMarca").find("option:selected").attr("selected", true);
  	})

	function validarAcesso(){

    	var renavam = $('#renavam').val();
    	
             
    if(renavam == "") {
		alert("O campo é Obrigatório");
	}else{
		$.ajax({
			url: "veiculobuscarPorRenavam",
			method: "POST",
			data: JSON.stringify({renavam: renavam}),
      		contentType: "application/json; charset=utf-8"
		}).done(function(retorno){
				if(retorno == "SUCCESS"){
					alert("Bem vindo");
				}else{
					 alert("Renavam incorreto")
				}
		});
	}
	}
   
   function sair(){
   	sessionStorage.setItem("usuariologado", "");
   	window.location.href = "index.html";
   }
   
   	function coletaDados(){
   	   var ids = document.getElementsByClassName('custom-control-input');
   	   coletaIDs(ids);         
   	}
   
   function botaoDeletarDaTelaVeiculo(){
   	var codigo= $('#idveiculo').val();
   	if(codigo!= null && codigo.trim()!=''){
   	deleteVeiculo(codigo);
   	}
   	document.getElementById('formVeiculo').reset();
   }
   
   function deleteVeiculo(codigo){
	
	if(confirm('Deseja realmente deletar?')){
   	
   	$.ajax({
	    		method: "DELETE",
	    		url: "veiculodelete",
	    		data : "iduser=" + codigo,
	    		success: function(response){
	    			$('#'+codigo).remove();
	    			alert(response);
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao deletar usuario por id:" + xhr.responseText);
	    	}); 
		}
   }


  function novoVeiculo(){
	document.getElementById('formVeiculo').reset()
	$("#cadastrar").remove();
	$("#novo").after('<button id="cadastrar" type="button" class="btn btn-secondary" onclick="salvarVeiculo()">Cadastrar</button>');
  }

function selecionaModelo(url, marca){


     //if(!url.includes("undefined") || url != undefined){
 	url = url +"/"+marca+"/models";
 	//}
 		$.ajax({
 	  				method: "GET",
 	  				url: url,
 	  				success: function(response){
 	  				$('#selecionarModelo > option').remove();
 					var obj = response;
 					//console.log(Object.values(obj.modelos[1])[0]);
 	  				for (var i = 0; i < obj.length; i++){
 					//console.log(Object.values(obj.modelos[0])[i]);
 	 				$('#selecionarModelo').append('<option value="'+Object.values(obj[i])[1]+'">'+Object.values(obj[i])[0]+'</option>');
 	 				}
 	  			}

 	  			});

 				$("#selecionarModelo").click(function () {
  	        		$("#selecionarModelo").each(function () {
  	         			 $(this).find("option").each(function () {
  	            			if ($(this).attr("selected")) {
 								//var comboModelo = document.getElementById("selecionarModelo");
 								var modelo = $(this).val();
 								//if (modelo!=0){
 								selecionaAno(url, modelo)
 								//}
  	            	 $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	       $("#selecionarModelo").find("option:selected").attr("selected", true);
  		})

 	}

 	function selecionaAno(url, modelo){


 	//modelo = $("#modelo option:selected").val();
    // if(!url.includes("undefined") || url != undefined){
 	url = url+"/"+modelo+"/years";
 	//}

 	$.ajax({
 	  		method: "GET",
 	  		url: url,
 	  		success: function(response){
 	  			$('#selecionarAno > option').remove();
 	  			for (var i = 0; i < response.length; i++){
 					//marca = response[i].codigo
 	 				$('#selecionarAno').append('<option value="'+response[i].code+'">'+response[i].name+'</option>');
 	 			}
 	  		}
 	  		//alert("Erro ao buscar cidade:" + xhr.responseText);
 	  	});

 	$("#selecionarAno").click(function () {
  	        $("#selecionarAno").each(function () {
  	          $(this).find("option").each(function () {
  	           if ($(this).attr("selected")) {

  	            $(this).removeAttr("selected");
  	            }
  	          });
  	        });
  	      $("#selecionarAno").find("option:selected").attr("selected", true);
  	})

    }

     function tabelafipe(marca, modelo, ano, index){

 	var valor;

 	  $.ajax({
  	    		method: "GET",
  	    		url: "https://parallelum.com.br/fipe/api/v2/cars/brands/"+marca+"/models/"+modelo+"/years/"+ano,
  	    		async: false,
  	    		success: function(response){
 					valor = response;
 					//}
 				}

 				//}).fail(function(xhr,status,errorThrown){
  	    		//alert("Erro ao buscar veiculo:" + xhr.responseText);
  	    		});

 	if (valor != null || valor != undefined){

 	//return valor;
 	return Object.values(valor)[index];

 	} else {

 	return "indefinido/nulo";

     }
 }

   function listarVeiculos(){

   var renavam = $('#renavam').val()

 		  $.ajax({
 	    		method: "GET",
 	    		url: "veiculobuscarPorRenavam",
 	    		data: "renavam=" + renavam,
 	    		success: function(response){
 	    			$('#tabelaveiculos > tbody > tr').remove();

					var rodizioativotemp;
 	    			var rodizio;
					var rodizioativo;
					
 	    			//for (var i = 0; i < response.length; i++){
	
 	    			  if(response.placa.charAt(response.placa.length-1) == 0  || response.placa.charAt(response.placa.length-1) ==1){
 	    			  rodizio = "Segunda Feira";
 	    			  rodizioativotemp = 1;
 	    			  }else if(response.placa.charAt(response.placa.length-1) == 2|| response.placa.charAt(response.placa.length-1) == 3){
					  rodizio  = "Terça Feira";
					  rodizioativotemp = 2;
					  }else if(response.placa.charAt(response.placa.length-1) == 4 || response.placa.charAt(response.placa.length-1)== 5){
					  rodizio = "Quarta Feira";
					  rodizioativotemp = 3;
					  }else if(response.placa.charAt(response.placa.length-1) ==  6 || response.placa.charAt(response.placa.length-1) == 7){
					  rodizio = "Quinta Feira";
					  rodizioativotemp = 4;
					  }else{
					  rodizio = "Sexta Feira";
					  rodizioativotemp = 5;
					  }
					  var d = new Date();
					   if(rodizioativotemp == d.getDay()){
					   rodizioativo = true;
					   }else{
					   rodizioativo = false;
					   }
		
						
 	    				$('#tabelaveiculos > tbody').append('<tr id="'+response.codigo+'">'+
   	   						'<td>'+response.codigo+'</td>'+
   	   						'<td>'+tabelafipe(response.marca,response.modelo,response.ano,1)+'</td>'+
   	   						'<td>'+tabelafipe(response.marca,response.modelo,response.ano,2)+'</td>'+
   	   						'<td>'+tabelafipe(response.marca,response.modelo,response.ano,3)+'</td>'+
   	   						'<td>'+rodizio+'</td>'+
   	   						'<td>'+rodizioativo+'</td>'+
							'<td>'+tabelafipe(response.marca,response.modelo,response.ano,0)+'</td>'+
							'<td>'+response.renavam+'</td>'+
							'<td>'+response.placa+'</td>'+
							'<td>'+response.cor+'</td>'+
							'<td><button type="button" class="btn btn-secondary" onclick="deleteVeiculo('+response.codigo+')">Deletar Veiculo</button></td></tr>');
   	    			//}

					}
					
					
				
 	    	}).fail(function(xhr,status,errorThrown){
 	    		alert("Erro ao buscar usuario:" + xhr.responseText);
 	    	}); 
     
   }
 
 function salvarVeiculo(){
 	
 	var codigo= $("#idveiculo").val();
 	var marca = $("#selecionarMarca option:selected").val();
	var modelo = $("#selecionarModelo option:selected").val();
	var ano = $("#selecionarAno option:selected").val();
	var renavam = $("#renavam_text").val();
	var placa = $("#placa_text").val();
	var cor = $("#cor_text").val();

		if(marca == null || marca != null && marca.trim()==''){
 		$("#selecionarMarca").focus();
 		alert('informe a marca');
 		return;
 	}
 	
 	if(modelo == null){
 		$("#selecionarModelo").focus();
 		alert('informe o modelo');
 		return;
 	}
 	
 	if(ano == null){
 		$("#selecionarAno").focus();
 		alert('informe a ano');
 		return;
 	}
 	
 	$.ajax({
 		method: "POST",
 		url: "veiculosalvar",
 		data : JSON.stringify({codigo: codigo, marca : marca, modelo : modelo, ano : ano, renavam : renavam, placa : placa, cor: cor}),
 		contentType: "application/json; charset=utf-8",
 		success: function(response){    		
 
 		var resposta = confirm("Você Deseja cadastrar esse registro?");
 			if (resposta == true){
 			$("#idveiculo").val(response.codigo);
 			alert("Salvo com Sucesso!")
 			}else{
 			alert("Registro Cancelado com Sucesso")
 			}
 		
 			
 	}
 	}).fail(function(xhr,status,errorThrown){
 		alert("Erro ao Salvar:" + xhr.responseText);
 	});
 	
 }