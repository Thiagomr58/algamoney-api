package com.example.algamoney.api.repository.lancamento;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.filter.LancamentoFilter;

import java.util.List;

/**
 * Created by Thiago Rodrigues on 10/02/2020
 */
public interface LancamentoRepositoryQuery {
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
