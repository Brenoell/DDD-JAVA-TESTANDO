package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.FilmeDAO;
import br.com.fiap.cineFiap.models.Filme;
import org.springframework.stereotype.Service;

public class FilmeService {

    @Service
    public class FilmeService{

        private final FilmeDAO filmeDAO;
        public FilmeService() {
            this.FilmeDAO = new FilmeDAO();
        }

    }
}
