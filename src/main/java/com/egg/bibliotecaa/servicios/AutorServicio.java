package com.egg.bibliotecaa.servicios;

import com.egg.bibliotecaa.entidades.Autor;
import com.egg.bibliotecaa.excepciones.MiException;
import com.egg.bibliotecaa.repositorio.AutorRepositorio;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre del autor no puede estar vacio o ser nulo");
        }

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList();

        autores = autorRepositorio.findAll();

        return autores;
    }

    public void modificarAutor(String nombre, String id) throws MiException {

        validar(nombre, id);

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        }
    }


    private void validar(String nombre, String id) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre del autor no pueder ser nulo o estar vacio");
        }
        if (id.isEmpty() || id == null) {
            throw new MiException("el id del autor no pueder ser nulo o estar vacio");
        }
    }


    @Transactional(readOnly = true)
    public Autor getOne(String id) {
        return autorRepositorio.getOne(id);
    }
}
