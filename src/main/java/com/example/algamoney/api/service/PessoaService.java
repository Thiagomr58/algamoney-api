package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Thiago Rodrigues on 07/02/2020
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Optional<Pessoa> pessoaSalva = buscarPessoaPeloCodigo(codigo);

        BeanUtils.copyProperties(pessoa, pessoaSalva.get(), "codigo"); // ignoro O Codigo, pois ele não vem no objeto e sim na url
        return pessoaRepository.save(pessoaSalva.get());

    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Optional<Pessoa> pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.get().setAtivo(ativo);
        pessoaRepository.save(pessoaSalva.get());
    }

    public Optional<Pessoa> buscarPessoaPeloCodigo(Long codigo) {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
        if (!pessoaSalva.isPresent()){
            throw  new EmptyResultDataAccessException(1);
        }
        return pessoaSalva;
    }

}
