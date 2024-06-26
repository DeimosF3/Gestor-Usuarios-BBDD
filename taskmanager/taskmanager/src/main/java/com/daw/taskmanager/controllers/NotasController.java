/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daw.taskmanager.controllers;

import com.daw.taskmanager.models.Nota;
import com.daw.taskmanager.models.NotaDTO;
import com.daw.taskmanager.services.notasRepository;
import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Rodrigo RL
 */
@Controller
@RequestMapping("/notas")
public class NotasController {

    @Autowired
    private notasRepository repo;
    private Date fecha = new Date();

    @GetMapping({"", "/"})
    public String showNotasList(Model model) {
//        List<Nota> notas = repo.findAll(Sort.by(Sort.Direction.DESC,"id")); ------ Utilizar esta línea para ordenar la lista en orden descendente
        List<Nota> notas = repo.findAll();
        model.addAttribute("notas", notas);
        return "notas/index";
    }

    @GetMapping({"/crear"})
    public String showCreatePage(Model model) {
        NotaDTO notaDTO = new NotaDTO();
        model.addAttribute("notaDTO", notaDTO);
        return "notas/crearNota"; // nombre del archivo html
    }

    @PostMapping("/crear")
    public String crearNota(
            @Valid @ModelAttribute NotaDTO NotaDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "notas/crearNota";
        }

        Nota nota = new Nota();
        nota.setNombre(NotaDTO.getNombre());
        nota.setDescripcion(NotaDTO.getDescripcion());
        nota.setFecha(fecha);
        nota.isCompletada();

        repo.save(nota);

        return "redirect:/notas";
    }

    @GetMapping("/editar")
    public String enseñarEditarPagina(
            Model model,
            @RequestParam int id
    ) {
        try {
            Nota nota = repo.findById(id).get();
            model.addAttribute("nota", nota);
            NotaDTO notaDTO = new NotaDTO();
            notaDTO.setNombre(nota.getNombre());
            notaDTO.setDescripcion(nota.getDescripcion());
            model.addAttribute("notaDTO", notaDTO);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/notas";
        }

        return "notas/editarNota";
    }

    @PostMapping("/editar")
    public String updateProduct(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute NotaDTO notaDTO,
            BindingResult result
    ) {

        try {
            Nota nota = repo.findById(id).get();
            model.addAttribute("nota", nota);
            if (result.hasErrors()) {
                return "notas/editarNota";
            }
            nota.setNombre(notaDTO.getNombre());
            nota.setDescripcion(notaDTO.getDescripcion());
            repo.save(nota);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());

        }
        return "redirect:/notas";
    }

    @GetMapping("/eliminar")
    public String eliminarProducto(
            @RequestParam int id
    ) {
        try {
            Nota nota = repo.findById(id).get();
            // eliminar producto de la bd
            repo.delete(nota);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/notas";
    }
}
