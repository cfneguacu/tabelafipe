

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
   
   function sair(){
   	sessionStorage.setItem("usuariologado", "");
   	window.location.href = "index.html";
   }
   
   function botaoDeletarDaTelaVeiculo(){
   	var codigo= $('#idveiculo').val();
   	if(codigo!= null && codigo.trim()!=''){
   	deleteVeiculo(codigo);
   	}
   	document.getElementById('formVeiculo').reset();
   }

    function botaoDeletarDaTelaUsuario(){
      	var codigo= $('#idusuario').val();
      	if(codigo!= null && codigo.trim()!=''){
      	deleteUsuario(codigo);
      	}
      	document.getElementById('formUsuario').reset();
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

    function deleteUsuario(codigo){

   	if(confirm('Deseja realmente deletar?')){

      	$.ajax({
   	    		method: "DELETE",
   	    		url: "usuariodelete",
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

   function novoUsuario(){
  	document.getElementById('formUsuario').reset()
  	$("#cadastrar").remove();
  	$("#novo").after('<button id="cadastrar" type="button" class="btn btn-secondary" onclick="salvarUsuario()">Cadastrar</button>');
    }

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

   function listarVeiculos(){

   var id = $('#user').val()

 		  $.ajax({
 	    		method: "GET",
 	    		url: "veiculoBuscaruserid",
 	    		data: "id=" + id,
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
		
						
 	    				$('#tabelaveiculos > tbody').append('<tr id="'+response.usuario_id+'">'+
   	   						'<td>'+response.usuario_id.nome+'</td>'+
   	   						'<td>'+response.data+'</td>'+
   	   						'<td>'+response.caracteristica_id.brand+'</td>'+
   	   						'<td>'+response.caracteristica_id.model+'</td>'+
   	   						'<td>'+response.caracteristica_id.modelYear+'</td>'+
   	   						'<td>'+rodizio+'</td>'+
   	   						'<td>'+rodizioativo+'</td>'+
							'<td>'+response.caracteristica_id.price+'</td>'+
							'<td>'+response.renavam+'</td>'+
							'<td>'+response.placa+'</td>'+
							'<td>'+response.cor+'</td>'+
							'<td>'+response.caracteristica_id.fuel+'</td>'+
							'<td><button type="button" class="btn btn-secondary" onclick="deleteVeiculo('+response.id+')">Deletar Veiculo</button></td></tr>');
   	    			//}

					}
					
					
				
 	    	}).fail(function(xhr,status,errorThrown){
 	    		alert("Erro ao buscar usuario:" + xhr.responseText);
 	    	}); 
     
   }

   function listarUsuario(){

      var id = $('#id_usuario').val()

    		  $.ajax({
    	    		method: "GET",
    	    		url: "usuariobuscaruserId",
    	    		data: "iduser=" + id,
    	    		success: function(response){
    	    			$('#tabelausuario > tbody > tr').remove();


    	    			//for (var i = 0; i < response.length; i++){



    	    				$('#tabelausuario > tbody').append('<tr id="'+response.id+'">'+
      	   						'<td id = "tb_nome">'+response.nome+'</td>'+
      	   						'<td id = "tb_email">'+response.email+'</td>'+
      	   						'<td id = "tb_cpf">'+response.cpf+'</td>'+
      	   						'<td id = "tb_data">'+response.data+'</td>'+
   							'<td><button type="button" class="btn btn-secondary" onclick="deleteUsuario('+response.id+')">Deletar Usuario</button></td></tr>');
      	    			//}

   					}



    	    	}).fail(function(xhr,status,errorThrown){
    	    		alert("Erro ao buscar usuario:" + xhr.responseText);
    	    	});

      }
 
 function salvarVeiculo(){

 	var id = $("#idveiculo").val();
 	var id_usuario = $('#idusuario').val();
 	var nome = $(this).closest('tr').find('td[id]').data("tb_nome");
 	var email = $(this).closest('tr').find('td[id]').data("tb_email");
 	var cpf = $(this).closest('tr').find('td[id]').data("tb_cpf");
 	var data = $(this).closest('tr').find('td[id]').data("tb_data");
 	var id_marca = $("#selecionarMarca option:selected").val();
	var id_modelo = $("#selecionarModelo option:selected").val();
	var ano = $("#selecionarAno option:selected").val();
	var combustivel = $("#selecionarCombustivel option:selected").val();
	var renavam = $("#renavam_text").val();
	var placa = $("#placa_text").val();
	var cor = $("#cor_text").val();


		if(id_marca == null || id_marca != null && id_marca.trim()==''){
 		$("#selecionarMarca").focus();
 		alert('informe a marca');
 		return;
 	}
 	
 	if(id_modelo == null){
 		$("#selecionarModelo").focus();
 		alert('informe o modelo');
 		return;
 	}
 	
 	if(ano == null){
 		$("#selecionarAno").focus();
 		alert('informe o Ano');
 		return;
 	}

 	if(combustivel == null){
     		$("#selecionarCombustivel").focus();
     		alert('informe o Combustivel');
     		return;
    }
 	
 	$.ajax({
 		method: "POST",
 		url: "veiculosalvar",
 		data : JSON.stringify(
 		{
 		 usuario_id: {
 		    nome: nome,
 		    email: email,
 		    cpf: cpf,
 		    data: data
 		 },
 		 id: id,
 		 modelo_id : {
 		     code : id_modelo,
 		     marca_id : {
                 code : id_marca
             },
 		 },
 		 ano_id : {
 		    code : ano
 		 },

 		 renavam : renavam,
 		 placa : placa,
 		 cor: cor,
 		 caracteristica_id: {
 		    fuel : combustivel
 		 }
 		 }),
 		contentType: "application/json; charset=utf-8",
 		success: function(response){
 
 		var resposta = confirm("Você Deseja cadastrar esse registro?");
 			if (resposta == true){
 			$("#idveiculo").val(response.id);
 			alert("Salvo com Sucesso!")
 			}else{
 			alert("Registro Cancelado com Sucesso")
 			}
 		
 			
 	}
 	}).fail(function(xhr,status,errorThrown){
 		alert("Erro ao Salvar:" + xhr.responseText);
 	});
 	
 }

 function salvarUsuario(){

  	var id_usuario = $('#idusuario').val();
  	var nome = $("#nome_text").val();
  	var email = $("#email_text").val();
  	var cpf = $("#cpf_text").val();
  	var data = $("#data_text").val();

  	$.ajax({
  		method: "POST",
  		url: "usuariosalvar",
  		data : JSON.stringify(
  		{
  		    nome: nome,
  		    email: email,
  		    cpf: cpf,
  		    data: data
  		 }),
  		contentType: "application/json; charset=utf-8",
  		success: function(response){

  		var resposta = confirm("Você Deseja cadastrar esse registro?");
  			if (resposta == true){
  			$("#idusuario").val(response.id);
  			alert("Salvo com Sucesso!")
  			}else{
  			alert("Registro Cancelado com Sucesso")
  			}


  	}
  	}).fail(function(xhr,status,errorThrown){
  		alert("Erro ao Salvar:" + xhr.responseText);
  	});

  	}