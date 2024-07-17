package br.com.springboot.tabelafipe.controller;

import br.com.springboot.tabelafipe.model.Usuario;
import br.com.springboot.tabelafipe.model.Veiculo;
import br.com.springboot.tabelafipe.service.UsuarioService;
import br.com.springboot.tabelafipe.service.VeiculoService;
import br.com.springboot.tabelafipe.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping(value = "usuariosalvar")
    @ResponseBody
    public void salvar(@RequestBody Usuario usuario) {
        usuarioService.inserir(usuario);
    }

    @DeleteMapping(value = "usuariodelete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
        usuarioService.deletar(iduser);
        return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "usuariobuscaruserId")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserId(@RequestParam(name = "iduser") Long iduser){
        Usuario usuario = usuarioService.buscarPorId(iduser);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PutMapping(value = "usuarioatualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario, @PathVariable("id") Long id){

        if(id == null) {
            return new ResponseEntity<String>("Id de usuario não foi informado para atualização", HttpStatus.OK);
        }

        usuarioService.atualizar(id, usuario);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @GetMapping(value = "usuariobuscar")
    @ResponseBody
    public ResponseEntity<String> buscarUsuario(@RequestParam(name = "iduser") Long iduser){
        String nome = usuarioService.buscarUsuario(iduser);
        return new ResponseEntity<String>(nome, HttpStatus.OK);
    }
}
