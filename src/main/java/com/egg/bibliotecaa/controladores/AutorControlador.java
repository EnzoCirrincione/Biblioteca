package com.egg.bibliotecaa.controladores;

import com.egg.bibliotecaa.entidades.Autor;
import com.egg.bibliotecaa.excepciones.MiException;
import com.egg.bibliotecaa.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/autor") //localhost:8080/autor
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar") //localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {
            autorServicio.crearAutor(nombre);
            modelo.put("exito", "El Autor fue registrado correctamente!");
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }

        return "index.html";
    }


    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_list.html";
    }

//como sabe el metodo que autor tiene que modificar, deberia viajar el id
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id , ModelMap modelo){
        modelo.put("autor",autorServicio.getOne(id));
        return "autor_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id ,String nombre, ModelMap modelo){
        try{
            autorServicio.modificarAutor(nombre, id);
            return "redirect:../lista";
        }catch (MiException ex){
            modelo.put("error", ex.getMessage());
            return "autor_modificar.html";

        }
    }



}
