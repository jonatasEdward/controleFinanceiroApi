package com.spring.boot.controlefinanceiro.service;

import com.spring.boot.controlefinanceiro.exception.custom.ApplicationException;
import com.spring.boot.controlefinanceiro.exception.custom.NotFoundException;
import com.spring.boot.controlefinanceiro.exception.custom.RegraNegocioException;
import com.spring.boot.controlefinanceiro.model.Grupo;
import com.spring.boot.controlefinanceiro.model.Pessoa;
import com.spring.boot.controlefinanceiro.repository.GrupoRepository;
import com.spring.boot.controlefinanceiro.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    private GrupoRepository repository;

    public GrupoService(GrupoRepository repository) {
        this.repository = repository;
    }

    public Grupo findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException("Grupo não encontrada"));
    }

    public List<Grupo> findAll() {
        return this.repository.findAll();
    }

    public Grupo save(Grupo grupo) {
        return this.repository.save(grupo);
    }

    public Grupo update(Grupo grupo) {
        Grupo firstGrupo = this.findById(grupo.getId());
        if (!grupo.getPessoa().getId().equals(firstGrupo.getPessoa().getId())) {
            throw new RegraNegocioException("Propriedade é uma informação que nao é permitido alterar.");
        }
        return this.repository.save(grupo);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
