package br.com.springboot.tabelafipe.service.impl;

import br.com.springboot.tabelafipe.model.Usuario;
import br.com.springboot.tabelafipe.model.Veiculo;
import br.com.springboot.tabelafipe.repository.UsuarioRepository;
import br.com.springboot.tabelafipe.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public void inserir(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizar(Long id, Usuario usuario) {
        Optional<Usuario> clienteBd = usuarioRepository.findById(id);
        if(clienteBd.isPresent()) {
            usuarioRepository.save(usuario);
        }
    }

    @Override
    public void deletar(Long id) {
            usuarioRepository.deleteById(id);
    }

    @Override
    public String buscarUsuario(Long id){
        return usuarioRepository.buscarUsuario(id);
    }
}
