package com.egg.bibliotecaa.repositorio;

import com.egg.bibliotecaa.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio  extends JpaRepository<Editorial,String> {
}
