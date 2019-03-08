package edu.eci.LibraryAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author carloscl
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class LibraryApiNoCotentException extends Exception{
    
    public LibraryApiNoCotentException(String mensaje) {
        super(mensaje);
    }
    
}
