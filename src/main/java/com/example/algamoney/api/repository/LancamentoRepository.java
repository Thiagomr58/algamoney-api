package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Thiago Rodrigues on 07/02/2020
 */
@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {


}
