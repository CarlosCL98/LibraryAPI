package edu.eci.LibraryAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author estudiante
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LibraryApiUnauthorizedException extends Exception{
    
    public LibraryApiUnauthorizedException(String mensaje) {
        super(mensaje);
    }
    
}
