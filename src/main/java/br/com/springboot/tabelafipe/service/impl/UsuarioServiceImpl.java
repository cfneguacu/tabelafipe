package br.com.springboot.tabelafipe.service.impl;

import br.com.springboot.tabelafipe.model.Usuario;
import br.com.springboot.tabelafipe.repository.UsuarioRepository;
import br.com.springboot.tabelafipe.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public void atualizar(Usuario usuario) {
       // Optional<Usuario> clienteBd = usuarioRepository.findById(id);
       // if(clienteBd.isPresent()) {
      //      usuarioRepository.save(usuario);
      //  }
    }

    @Override
    public void deletar(Long id) {
            usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario buscarUsuario(String cpf){
        return usuarioRepository.buscarUsuario(cpf);
    }

    @Override
    public Page<Usuario> getTaskListPaginated(int selectedPage, int pageSize, String globalStatus) {
        return null;
    }
}
