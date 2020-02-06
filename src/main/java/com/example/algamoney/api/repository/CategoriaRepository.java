package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Thiago Rodrigues on 06/02/2020
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
