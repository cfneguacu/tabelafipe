package br.com.springboot.tabelafipe.service;

import br.com.springboot.tabelafipe.model.Veiculo;

public interface VeiculoService {

    Iterable<Veiculo> buscarTodos();

    Veiculo buscarPorId(Long id);

    void inserir(Veiculo veiculo);

    void atualizar(Long id, Veiculo veiculo);

    void deletar(Long id);

    Veiculo buscarPorRenavam(String nome);

}
