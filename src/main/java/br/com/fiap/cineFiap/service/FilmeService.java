
package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.FilmeDAO;
import br.com.fiap.cineFiap.models.Filme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private FilmeDAO dao;

    public FilmeService() {
        dao = new FilmeDAO();
    }

    public void cadastrar(Filme filme) {

        if (filme.getDuracao() <= 0) {
            System.out.println("ERRO: A duração deve ser maior que zero.");
            return;
        }

        if (filme.getClassificacao() == null) {
            System.out.println("ERRO: A classificação indicativa é obrigatória.");
            return;
        }

        if (filme.getCategoria() == null) {
            System.out.println("ERRO: A categoria é obrigatória.");
            return;
        }

        dao.cadastro(filme);
    }

    public void excluir(long id) {
        dao.exclusao(id);
    }

    public void alterar(Filme filme) {
        dao.alterar(filme);
    }

    public Filme consultarPorNome(String nome) {
        return dao.consultaNomeFilme(nome);
    }

    public List<Filme> consultarPorAno(int ano) {
        return dao.consultaAnoFilme(ano);
    }
}

