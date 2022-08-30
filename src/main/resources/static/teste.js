var url = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
 
	   $.ajax({
	  		method: "GET",
	  		url: url,
	  		success: function(response){
	  			$('#selecionarMarca > option').remove();
	  			for (var i = 0; i < response.length; i++){
					//marca = response[i].codigo
	 				$('#selecionarMarca').append('<option value="'+response[i].codigo+'">'+response[i].nome+'</option>');
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
    	
    	var email = $('#email_login').val();
    	var cpf = $('#cpf_login').val();
    	
             
    if(email == "" || cpf  == "") {
		alert("Todos os campos são obrigatórios");
	}else{
		$.ajax({
			url: "logado",
			method: "POST",
			data: JSON.stringify({email : email, cpf : cpf}),
      		contentType: "application/json; charset=utf-8"
		}).done(function(retorno){
				if(retorno == "SUCCESS"){
					alert("Bem vindo "+email);
				}else{
					 alert("Email ou Cpf incorreto")
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
   
   function botaoDeletarDaTela(){
   	var codigo= $('#codigo').val();
   	if(codigo!= null && codigo.trim()!=''){
   	deleteCadastro(codigo);
   	}
   	document.getElementById('formCadastro').reset();
   }
   
   function deleteCadastro(codigo){
	
	if(confirm('Deseja realmente deletar?')){
   	
   	$.ajax({
	    		method: "DELETE",
	    		url: "cadastrodelete",
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
   
   function botaoDeletarDaTelaVeiculo(){
   	var codigo= $('#idveiculo').val();
   	if(codigo!= null && codigo.trim()!=''){
   	deleteCadastro(codigo);
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
   
function colocarEmEdicaoAdm(codigo){
		
   	$.ajax({
	    		method: "GET",
	    		url: "veiculobuscaruserId",
	    		data : "iduser=" + codigo,
	    		success: function(response){
	    		$("#idveiculo").val(response.codigo);
   				$("#marca").val(response.nome);
	   			$("#modelo").val(response.usuario);
	   			$("#ano").val(response.senha);
	    		$("#cadastrar").remove();
	    		$("#novo").after(' '+'<button id="cadastrar" type="button" class="btn btn-secondary" onclick="salvarVeiculo()">Cadastrar</button>');

				
	    	  //  $("#modalPesquisar").modal('hide');
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao buscar usuario por id:" + xhr.responseText);
	    	}); 
}

  function novoVeiculo(){
	document.getElementById('formVeiculo').reset()
	$("#cadastrar").remove();
	$("#novo").after('<button id="cadastrar" type="button" class="btn btn-secondary" onclick="validaDuplicadosVeiculo()">Cadastrar</button>');
  }
  
  function novoUsuario(){
		document.getElementById('formCadastro').reset()
		$("#salvar").remove();
		$("#botoes").append('<button id="salvar" type="button" class="btn btn-primary" onclick="validaDuplicados()">Salvar</button>');
	  }
   
function colocarEmEdicao(codigo){
		
   	$.ajax({
	    		method: "GET",
	    		url: "cadastrobuscaruserId",
	    		data : "iduser=" + codigo,
	    		success: function(response){
	    		$("#codigo").val(response.codigo);
   				$("#nome").val(response.nome);
	   			$("#email").val(response.nome);
				$("#cpf").val(response.nome);
				$("#datanasc").val(response.datanasc);
				$("#salvar").remove();
	    		$("#botoes").append(' '+'<button id="salvar" type="button" class="btn btn-primary" onclick="salvarUsuario()">Salvar</button>');

	    	  //  $("#modalPesquisar").modal('hide');
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao buscar usuario por id:" + xhr.responseText);
	    	}); 
   	
   	$('#myTab li:nth-child(3) button').tab('show');
}


function selecionaModelo(url, marca){
	
	
    //if(!url.includes("undefined") || url != undefined){
	url = url +"/"+marca+"/modelos";
	//}
		$.ajax({
	  				method: "GET",
	  				url: url,
	  				success: function(response){
	  				$('#selecionarModelo > option').remove();
					var obj = response;
					//console.log(Object.values(obj.modelos[1])[0]);
	  				for (var i = 0; i < obj.modelos.length; i++){
					//console.log(Object.values(obj.modelos[0])[i]);
	 				$('#selecionarModelo').append('<option value="'+Object.values(obj.modelos[i])[1]+'">'+Object.values(obj.modelos[i])[0]+'</option>');
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
	url = url+"/"+modelo+"/anos";
	//}
	
	$.ajax({
	  		method: "GET",
	  		url: url,
	  		success: function(response){
	  			$('#selecionarAno > option').remove();
	  			for (var i = 0; i < response.length; i++){
					//marca = response[i].codigo
	 				$('#selecionarAno').append('<option value="'+response[i].codigo+'">'+response[i].nome+'</option>');
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

    function tabelafipemarca(marca, modelo, ano){
	
	var valor;
	
	  $.ajax({
 	    		method: "GET",
 	    		url: "https://parallelum.com.br/fipe/api/v1/carros/marcas/"+marca+"/modelos/"+modelo+"/anos/"+ano,
 	    		async: false,
 	    		success: function(response){
	//console.log(response);
					//if (response != null || response != undefined){
					valor = response;
					//}
				}
				
				//}).fail(function(xhr,status,errorThrown){
 	    		//alert("Erro ao buscar veiculo:" + xhr.responseText);
 	    		}); 	

	if (valor != null || valor != undefined){
		
	//return valor;

	return Object.values(valor)[1];
	
	} else {
		
	return valor = "indefinido/nulo";

    }
}

   function tabelafipemodelo(marca, modelo, ano){
	
	var valor;
	
	  $.ajax({
 	    		method: "GET",
 	    		url: "https://parallelum.com.br/fipe/api/v1/carros/marcas/"+marca+"/modelos/"+modelo+"/anos/"+ano,
 	    		async: false,
 	    		success: function(response){
	//console.log(response);
					//if (response != null || response != undefined){
					valor = response;
					//}
				}
				
				//}).fail(function(xhr,status,errorThrown){
 	    		//alert("Erro ao buscar veiculo:" + xhr.responseText);
 	    		}); 	

	if (valor != null || valor != undefined){
		
	return Object.values(valor)[2];
	
	} else {
		
	return valor = "indefinido/nulo";

    }
}

   function tabelafipeano(marca, modelo, ano){
	
	var valor;
	
	  $.ajax({
 	    		method: "GET",
 	    		url: "https://parallelum.com.br/fipe/api/v1/carros/marcas/"+marca+"/modelos/"+modelo+"/anos/"+ano,
 	    		async: false,
 	    		success: function(response){
	//console.log(response);
					//if (response != null || response != undefined){
					valor = response;
					//}
				}
				
				//}).fail(function(xhr,status,errorThrown){
 	    		//alert("Erro ao buscar veiculo:" + xhr.responseText);
 	    		}); 	

	if (valor != null || valor != undefined){
		
	//return valor;

	return Object.values(valor)[3];
	
	} else {
		
	return valor = "indefinido/nulo";

    }
}

   function tabelafipevalor(marca, modelo, ano){
	
	var valor;
	
	  $.ajax({
 	    		method: "GET",
 	    		url: "https://parallelum.com.br/fipe/api/v1/carros/marcas/"+marca+"/modelos/"+modelo+"/anos/"+ano,
 	    		async: false,
 	    		success: function(response){
	//console.log(response);
					//if (response != null || response != undefined){
					valor = response;
					//}
				}
				
				//}).fail(function(xhr,status,errorThrown){
 	    		//alert("Erro ao buscar veiculo:" + xhr.responseText);
 	    		}); 	

	if (valor != null || valor != undefined){

	return Object.values(valor)[0];
	
	} else {
		
	return valor = "indefinido/nulo";

    }
}

   function listarVeiculos(){
       	
 	validarAcesso();

 		  $.ajax({
 	    		method: "GET",
 	    		url: "veiculolistatodos",
 	    		//data : "name=" + nome ,
 	    		success: function(response){
				///console.log(response);
 	    			$('#tabelaveiculos > tbody > tr').remove();

					var rodizioativotemp;
 	    			var rodizio = [];
					var rodizioativo = [];
					
 	    			for (var i = 0; i < response.length; i++){	
	
 	    			  if(response[i].ano.charAt(response[i].ano.length-1) == 0  || response[i].ano.charAt(response[i].ano.length-1) ==1){
 	    			  rodizio[i] = "Segunda Feira";
 	    			  rodizioativotemp = 1;
 	    			  }else if(response[i].ano.charAt(response[i].ano.length-1) == 2|| response[i].ano.charAt(response[i].ano.length-1) == 3){
					  rodizio[i]  = "Terça Feira";
					  rodizioativotemp = 2;
					  }else if(response[i].ano.charAt(response[i].ano.length-1) == 4 || response[i].ano.charAt(response[i].ano.length-1)== 5){
					  rodizio[i] = "Quarta Feira";
					  rodizioativotemp = 3;
					  }else if(response[i].ano.charAt(response[i].ano.length-1) ==  6 || response[i].ano.charAt(response[i].ano.length-1) == 7){
					  rodizio[i] = "Quinta Feira";
					  rodizioativotemp = 4;
					  }else{
					  rodizio[i] = "Sexta Feira";
					  rodizioativotemp = 5;
					  }
					  var d = new Date();
					   if(rodizioativotemp == d.getDay()){
					   rodizioativo[i] = true;
					   }else{
					   rodizioativo[i] = false;
					   }
		
						
 	    				$('#tabelaveiculos > tbody').append('<tr id="'+response[i].codigo+'">'+
   	   						'<td>'+response[i].codigo+'</td>'+ 
   	   						'<td>'+tabelafipemarca(response[i].marca,response[i].modelo,response[i].ano)+'</td>'+
   	   						'<td>'+tabelafipemodelo(response[i].marca,response[i].modelo,response[i].ano)+'</td>'+
   	   						'<td>'+tabelafipeano(response[i].marca,response[i].modelo,response[i].ano)+'</td>'+
   	   						'<td>'+rodizio[i]+'</td>'+
   	   						'<td>'+rodizioativo[i]+'</td>'+
							'<td>'+tabelafipevalor(response[i].marca,response[i].modelo,response[i].ano)+'</td></tr>');
   	    			}

 	    			$('#quantidade').remove();
 	    			$('#registrosencontrados').append('<label id = "quantidade">'+response.length+'</label>');
					}
					
					
				
 	    	}).fail(function(xhr,status,errorThrown){
 	    		alert("Erro ao buscar usuario:" + xhr.responseText);
 	    	}); 
     
   }
     
   function validaDuplicados(){
   	
   	var nome = $("#nome").val();

   	  $.ajax({
	    		method: "GET",
	    		url: "validaDuplicados",
	    		data : "name=" + nome ,
 	 			success: function(response){
	    		if(response > 0){
		    		alert("Cadastro Duplicado");
	    		}else{
	    			salvarUsuario();
	    		}
	    		
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao validar cadastro:" + xhr.responseText);
	    	}); 
 	
     }
   
 function salvarUsuario(){
   	
   		var codigo= $("#codigo").val();
   		var nome = $("#nome").val();
   		var email = $("#email").val();
  	 	var cpf = $("#cpf").val();
		var datanasc = $("#datanasc").val();
	   

		if(nome == null || nome != null && nome.trim()==''){
   		$("#nome").focus();
   		alert('informe o nome');
   		return;
   		}

	if(email == null || email != null && email.trim()==''){
   		$("#email").focus();
   		alert('informe o nome');
   		return;
   	}

	if(cpf == null || cpf != null && cpf.trim()==''){
   		$("#cpf").focus();
   		alert('informe o nome');
   		return;
   	}
   	
   	if(datanasc == null){
   		$("#datanasc").focus();
   		alert('informe a data de nascimento');
   		return;
   	}
   	
   	$.ajax({
   		method: "POST",
   		url: "cadastrosalvar",
   		data : JSON.stringify({codigo: codigo, nome : nome, email : email, cpf : cpf, datanasc : datanasc}),
   		contentType: "application/json; charset=utf-8",
   		success: function(response){    		
   
   		var resposta = confirm("Você Deseja cadastrar esse registro?");
   			if (resposta == true){
   			$("#codigo").val(response.codigo);
   			alert("Salvo com Sucesso!")
   			}else{
   			alert("Registro Cancelado com Sucesso")
   			}
   		
   			
   	}
   	}).fail(function(xhr,status,errorThrown){
   		alert("Erro ao Salvar:" + xhr.responseText);
   	});
   	
   }
 
 function salvarVeiculo(){
 	
 	var codigo= $("#idveiculo").val();
 	var marca = $("#selecionarMarca option:selected").val();
	var modelo = $("#selecionarModelo option:selected").val();
	var ano = $("#selecionarAno option:selected").val();

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
 		data : JSON.stringify({codigo: codigo, marca : marca, modelo : modelo, ano : ano}),
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