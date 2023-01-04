package com.egg.bibliotecaa.servicios;

import com.egg.bibliotecaa.entidades.Editorial;
import com.egg.bibliotecaa.excepciones.MiException;
import com.egg.bibliotecaa.repositorio.EditorialRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorial;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException{

        if(nombre.isEmpty()|| nombre==null){
            throw new MiException("el nombre de la editorial no pueder ser nulo o estar vacio");
        }

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    public List<Editorial> listarEditoriales(){

        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRepositorio.findAll();

        return editoriales;
    }


    public void modificarEditorial(String nombre , String id) throws MiException {

        validar(nombre,id);

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        }
    }

    private void validar(String nombre , String id) throws MiException {

        if(nombre.isEmpty()|| nombre==null){
            throw new MiException("el nombre de la editorial no pueder ser nulo o estar vacio");
        }
        if(id.isEmpty()|| id==null){
            throw new MiException("el id de la editorial no pueder ser nulo o estar vacio");
        }
    }
}
