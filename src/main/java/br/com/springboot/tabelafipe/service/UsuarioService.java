package br.com.springboot.tabelafipe.service;

import br.com.springboot.tabelafipe.model.Usuario;
import br.com.springboot.tabelafipe.model.Veiculo;
import org.springframework.data.domain.Page;

public interface UsuarioService {

    Usuario buscarPorId(Long id);

    void inserir(Usuario usuario);

    void atualizar(Usuario usuario);

    void deletar(Long id);

    Usuario buscarUsuario(String cpf);

    Page<Usuario> getTaskListPaginated(int selectedPage, int pageSize, String globalStatus);
}
