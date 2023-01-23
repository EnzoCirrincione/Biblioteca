package com.egg.bibliotecaa.controladores;


import com.egg.bibliotecaa.excepciones.MiException;
import com.egg.bibliotecaa.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial") //localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar")
    private String registrar(){
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        try{
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito","La editorial fue registrada correctamente");
        }catch(MiException ex){

            modelo.put("error",ex.getMessage());
            return "editorial_form.html";
        }
        return "index.html";
    }

}
