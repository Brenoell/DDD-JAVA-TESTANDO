package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Filme;
import br.com.fiap.cineFiap.service.FilmeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeService service;

    public FilmeController() {
        service = new FilmeService();
    }

    @PostMapping
    public void cadastrar(@RequestBody Filme filme) {
        service.cadastrar(filme);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable long id) {
        service.excluir(id);
    }

    @PutMapping
    public void alterar(@RequestBody Filme filme) {
        service.alterar(filme);
    }

    @GetMapping("/nome/{nome}")
    public Filme consultarPorNome(@PathVariable String nome) {
        return service.consultarPorNome(nome);
    }

    @GetMapping("/ano/{ano}")
    public List<Filme> consultarPorAno(@PathVariable int ano) {
        return service.consultarPorAno(ano);
    }


}

