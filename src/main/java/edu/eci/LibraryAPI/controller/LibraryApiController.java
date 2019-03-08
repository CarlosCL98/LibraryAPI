package edu.eci.LibraryAPI.controller;

import edu.eci.LibraryAPI.exception.LibraryApiException;
import edu.eci.LibraryAPI.exception.LibraryApiNoCotentException;
import edu.eci.LibraryAPI.exception.LibraryApiNotFoundException;
import edu.eci.LibraryAPI.exception.LibraryApiUnauthorizedException;
import edu.eci.LibraryAPI.model.Libreria;
import edu.eci.LibraryAPI.model.Libro;
import edu.eci.LibraryAPI.services.LibraryApiServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author estudiante
 */
@RestController
@RequestMapping(value = "/LibraryAPI")
public class LibraryApiController {

    @Autowired
    LibraryApiServices libraryApiServices;
    
    /*
      ######################### Peticiones GET #########################
    */
    @RequestMapping(value = "/1.0/libraries", method = RequestMethod.GET)
    public ResponseEntity<List<Libreria>> manejadorGetRecursoLibrerias() throws LibraryApiNoCotentException {
        try {
            List<Libreria> libraries = libraryApiServices.getAllLibraries();
            return new ResponseEntity<>(libraries, HttpStatus.OK);
        } catch (LibraryApiException ex) {
            throw new LibraryApiNoCotentException(ex.getMessage());
        }
        
    }

    @RequestMapping(value = "/1.0/libraries/{idL}", method = RequestMethod.GET)
    public ResponseEntity<Libreria> manejadorGetRecursoLibreria(@PathVariable int idL) throws LibraryApiNotFoundException {
        try {
            Libreria library = libraryApiServices.getLibraryById(idL);
            return new ResponseEntity<>(library, HttpStatus.OK);
        } catch (LibraryApiException ex) {
            throw new LibraryApiNotFoundException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/1.0/libraries/{idL}/books", method = RequestMethod.GET)
    public ResponseEntity<List<Libro>> manejadorGetRecursoLibros(@PathVariable int idL) throws LibraryApiNotFoundException {
        try {
            List<Libro> books = libraryApiServices.getBooksByLibraryId(idL);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (LibraryApiException ex) {
            throw new LibraryApiNotFoundException(ex.getMessage());
        }
    }
    
    @RequestMapping(value = "/1.0/libraries/{idL}/books/{idB}", method = RequestMethod.GET)
    public ResponseEntity<Libro> manejadorGetRecursoLibro(@PathVariable int idL, @PathVariable int idB) throws LibraryApiNotFoundException {
        try {
            Libro book = libraryApiServices.getBook(idL, idB);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (LibraryApiException ex) {
            throw new LibraryApiNotFoundException(ex.getMessage());
        }
    }
    
    /*
      ######################### Peticiones POST #########################
    */
    @RequestMapping(value = "/1.0/libraries", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoLibreria(@RequestBody Libreria library) throws LibraryApiNotFoundException {
        try {
            libraryApiServices.addNewLibrary(library);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (LibraryApiException ex) {
            throw new LibraryApiNotFoundException(ex.getMessage());
        }
    }
    
    @RequestMapping(value = "/1.5/libraries/{email}", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoLibreriaAsincrona(@RequestBody Libreria library, @PathVariable String email) throws LibraryApiNotFoundException {
        try {
            LibraryAPIThread t = new LibraryAPIThread(library.getId(), email, "Creación librería finalizada", "La librería '"+library.getId()+":"+library.getNombre()+"' se ha añadido correctamente.\n\n");
            t.start();
            libraryApiServices.addNewLibrary(library);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (LibraryApiException ex) {
            throw new LibraryApiNotFoundException(ex.getMessage());
        }
    }
    
    @RequestMapping(value = "/1.0/libraries/{idL}", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoLibro(@RequestBody Libro book, @PathVariable int idL) throws LibraryApiNotFoundException {
        try {
            libraryApiServices.addBookToALibrary(idL, book);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (LibraryApiException ex) {
            throw new LibraryApiNotFoundException(ex.getMessage());
        }
    }
    
    @RequestMapping(value = "/1.0/libraries/{idL}/{email}", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoLibroAsincrono(@RequestBody Libro book, @PathVariable int idL, @PathVariable String email) throws LibraryApiNotFoundException {
        try {
            LibraryAPIThread t = new LibraryAPIThread(idL, email, "Creacion del libro finalizada", "El libro '"+book.getNombre()+"' se ha añadido correctamente a la libreria '"+idL+"'.\n\n");
            t.start();
            libraryApiServices.addBookToALibrary(idL, book);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (LibraryApiException ex) {
            throw new LibraryApiNotFoundException(ex.getMessage());
        }
    }
    
    /*
      ######################### Peticiones DELETE #########################
    */
    @RequestMapping(value = "/1.0/libraries", method = RequestMethod.DELETE)
    public ResponseEntity<?> manejadorDeleteRecursoLibro(@RequestBody int idL) throws LibraryApiNotFoundException, LibraryApiUnauthorizedException {
        try {
            libraryApiServices.deleteLibrary(idL);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (LibraryApiException ex) {
            if (ex.getMessage().equals("La libreria '"+idL+"' no puede ser eliminada, porque contiene libros.")) {
                throw new LibraryApiUnauthorizedException(ex.getMessage());
            } else {
                throw new LibraryApiNotFoundException(ex.getMessage());
            }
        }
    }
    
}
