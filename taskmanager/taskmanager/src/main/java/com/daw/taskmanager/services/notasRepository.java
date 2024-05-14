/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daw.taskmanager.services;

import com.daw.taskmanager.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rodrigo RL
 */
public interface notasRepository extends JpaRepository<Nota, Integer> {
    
}
