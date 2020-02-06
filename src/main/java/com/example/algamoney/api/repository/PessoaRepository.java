package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Thiago Rodrigues on 06/02/2020
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
