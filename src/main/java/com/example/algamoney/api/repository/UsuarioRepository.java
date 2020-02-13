package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Thiago Rodrigues on 13/02/2020
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(String email);

}
