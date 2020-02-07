package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Thiago Rodrigues on 07/02/2020
 */
@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento save(Lancamento lancamento) {
            Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

            if (!pessoa.isPresent() || !pessoa.get().isInativo()){
                    throw  new PessoaInexistenteOuInativaException();
            }

        return lancamentoRepository.save(lancamento);

    }
}
