package br.com.springboot.tabelafipe.service;

import br.com.springboot.tabelafipe.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    Iterable<Veiculo> buscarTodos();

    List<Veiculo> buscarPorId(Long id);

    void inserir(Veiculo veiculo);

    void atualizar(Long id, Veiculo veiculo);

    void deletar(Long id);

    Veiculo buscarPorRenavam(String nome);

}
