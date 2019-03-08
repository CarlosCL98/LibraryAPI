/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.LibraryAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author estudiante
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LibraryApiNotFoundException extends Exception{
    
    public LibraryApiNotFoundException(String mensaje) {
        super(mensaje);
    }
    
}
