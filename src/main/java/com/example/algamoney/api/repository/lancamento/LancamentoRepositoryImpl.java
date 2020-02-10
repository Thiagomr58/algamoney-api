package com.example.algamoney.api.repository.lancamento;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.filter.LancamentoFilter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiago Rodrigues on 10/02/2020
 */
public class LancamentoRepositoryImpl implements  LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
        // Criteria do JPA
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root  = criteria.from(Lancamento.class); // uso o root para fazer os filtros, adicionar retrições no where (Pegar os atributos de Lançamento)

        // Criar as retrições
        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {

        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) { // usa classe do proprio spring para verificar se a string é vazia
            predicates.add(builder.like(
                    // Não consegui usar o metamodel do JPA Lancamento_.descricao, usei a forma tradicional, passando uma string com o nome do atributo
                    builder.lower(root.get("descricao")), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"
            ));
        }

        if(lancamentoFilter.getDataVencimentoDe() != null) {
             predicates.add(
                     builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe()));
        }

        if(lancamentoFilter.getDataVencimentoAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoAte()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }


}
