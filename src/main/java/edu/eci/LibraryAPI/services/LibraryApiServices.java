package edu.eci.LibraryAPI.services;

import edu.eci.LibraryAPI.exception.LibraryApiException;
import edu.eci.LibraryAPI.model.Libreria;
import edu.eci.LibraryAPI.model.Libro;
import edu.eci.LibraryAPI.persistence.InMemoryLibraryApiPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author estudiante
 */
@Service
public class LibraryApiServices {
    
    @Autowired
    private InMemoryLibraryApiPersistence libraryApiPersistence;
    
    private Map<Integer, Libreria> libraries = new HashMap<>();
    
    public List<Libreria> getAllLibraries() {
        return libraryApiPersistence.getAllLibraries();
    }
    
    public Libreria getLibraryById(int id) throws LibraryApiException {
        return libraryApiPersistence.getLibraryById(id);
    }
    
    public Libreria getLibraryByName(String nombre) throws LibraryApiException {
        return libraryApiPersistence.getLibraryByName(nombre);
    }
    
    public List<Libro> getBooksByLibraryId(int id) throws LibraryApiException {
        return libraryApiPersistence.getBooksByLibraryId(id);
    }
    
    public Libro getBook(int idL, int idB) throws LibraryApiException {
        return libraryApiPersistence.getBook(idL, idB);
    }
    
    public void addNewLibrary(Libreria library) throws LibraryApiException {
        libraryApiPersistence.addNewLibrary(library);
    }
    
    public void addBookToALibrary(int id, Libro book) throws LibraryApiException {
        libraryApiPersistence.addBookToALibrary(id, book);
    }
    
    public void deleteLibrary(int id) throws LibraryApiException {
        libraryApiPersistence.deleteLibrary(id);
    }
    
    public Map<Integer, Libreria> getLibraries() {
        return libraryApiPersistence.getLibraries();
    }

    public void setLibraries(Map<Integer, Libreria> libraries) {
        libraryApiPersistence.setLibraries(libraries);
    }
    
}
