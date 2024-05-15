/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daw.taskmanager.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Rodrigo RL
 */
public class NotaDTO {
    @NotEmpty(message = "Nombre obligatorio")
    private String nombre;
    @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres")
    @Size(max = 2000, message = "La descripción no puede exceder de 2000 caracteres")
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
