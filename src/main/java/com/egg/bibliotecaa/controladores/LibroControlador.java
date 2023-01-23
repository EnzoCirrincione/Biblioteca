package com.egg.bibliotecaa.controladores;

import com.egg.bibliotecaa.entidades.Autor;
import com.egg.bibliotecaa.entidades.Editorial;
import com.egg.bibliotecaa.entidades.Libro;
import com.egg.bibliotecaa.excepciones.MiException;
import com.egg.bibliotecaa.servicios.AutorServicio;
import com.egg.bibliotecaa.servicios.EditorialServicio;
import com.egg.bibliotecaa.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("autores",autores);
        modelo.addAttribute("editoriales",editoriales);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn , @RequestParam String titulo,
                           @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor ,
                           @RequestParam String idEditorial , ModelMap modelo) throws MiException{

        try{

            libroServicio.crearLibro(isbn,titulo,ejemplares,idAutor,idEditorial);

            modelo.put("exito","El libro fue cargado corectamente!!");


        }catch (MiException ex){

            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();

            modelo.addAttribute("autores",autores);
            modelo.addAttribute("editoriales",editoriales);

            modelo.put("error",ex.getMessage());

            return "libro_form.html"; //volvemos a cargar el formulario
        }
        return "index.html";
    }


    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Libro> libros= libroServicio.listarLibros();
        modelo.addAttribute("libros",libros);
        return "libro_list.html";
    }
}
