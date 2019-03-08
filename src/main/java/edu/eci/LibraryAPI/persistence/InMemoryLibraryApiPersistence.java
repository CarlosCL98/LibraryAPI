package edu.eci.LibraryAPI.persistence;

import edu.eci.LibraryAPI.exception.LibraryApiException;
import edu.eci.LibraryAPI.model.Libreria;
import edu.eci.LibraryAPI.model.Libro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 *
 * @author estudiante
 */
@Service
public class InMemoryLibraryApiPersistence {
    
    private Map<Integer, Libreria> libraries = new HashMap<>();
    
    public InMemoryLibraryApiPersistence() {
        // Cargar datos.
        Map<Integer, Libro> books1 = new HashMap<>();
        Libro book1 = new Libro(1, "Percy Jackson y el ladron del rayo", "Rick Riordan", "Libro mitologico de dioses sobre la vida de Zeus y Poseidon.");
        Libro book2 = new Libro(2, "Ensayo sobre la ceguera", "Jose Saramago", "Basado en una historia ficticia, en donde lsa peronas que comienzan a quedarse ciegas, deben luchas contra ello.");
        Libro book3 = new Libro(3, "Harry Potter y las reliquias de la muerte", "", "Mundo de magia, en el que <harry debe poder sobrevivir y salvar a sus amigos.");
        books1.put(1, book1);
        books1.put(2, book2);
        books1.put(3, book3);
        Libreria l1 = new Libreria(1, "LibreriaSantaFe", "Cra 12 #185-45", "3214502360", books1);
        libraries.put(1, l1);
        
        Map<Integer, Libro> books2 = new HashMap<>();
        Libro book4 = new Libro(1, "El caballo de troya", "Luis Sarmiento", "Un joven viaja al pasado a encontrarse con sus antepasados.");
        Libro book5 = new Libro(2, "Ensayo sobre la ceguera", "Jose Saramago", "Basado en una historia ficticia, en donde lsa peronas que comienzan a quedarse ciegas, deben luchas contra ello.");
        Libro book6 = new Libro(3, "Harry Potter y las reliquias de la muerte", "", "Mundo de magia, en el que <harry debe poder sobrevivir y salvar a sus amigos.");
        books2.put(1, book4);
        books2.put(2, book5);
        books2.put(3, book6);
        Libreria l2 = new Libreria(2, "LibreriaNacional", "Cra 15 #45-3 sur", "4562107", books2);
        libraries.put(2, l2);

    }
    
    public List<Libreria> getAllLibraries() {
        List<Libreria> lb = new ArrayList<>();
        for (Integer libreria : libraries.keySet()) {
            lb.add(libraries.get(libreria));
        }
        return lb;
    }
    
    public Libreria getLibraryById(int id) throws LibraryApiException {
        if (!libraries.containsKey(id)) {
            throw new LibraryApiException("La libreria con id "+id+" no existe.");
        }
        return libraries.get(id);
    }
    
    public Libreria getLibraryByName(String nombre) throws LibraryApiException {
        Libreria library = null;
        for (Integer libreria : libraries.keySet()) {
            if (nombre.equals(libraries.get(libreria).getNombre())) {
                library = libraries.get(libreria);
            }
        }
        if (library == null) {
            throw new LibraryApiException("La libreria '"+nombre+"' no existe.");
        }
        return library;
    }
    
    public List<Libro> getBooksByLibraryId(int id) throws LibraryApiException {
        Libreria library = getLibraryById(id);
        Map<Integer, Libro> books = library.getLibros();
        List<Libro> booksGet = new ArrayList<>();
        for (Integer book : books.keySet()) {
            booksGet.add(books.get(book));
        }
        return booksGet;
    }
    
    public Libro getBook(int idL, int idB) throws LibraryApiException {
        Libreria library = getLibraryById(idL);
        Map<Integer, Libro> books = library.getLibros();
        if (!books.containsKey(idB)) {
            throw new LibraryApiException("El libro "+idB+" no se encuentra en la libreria "+idL+".");
        }
        Libro book = books.get(idB);
        return book;
    }
    
    public void addNewLibrary(Libreria library) throws LibraryApiException {
        if (libraries.containsKey(library.getId())) {
            throw new LibraryApiException("La libreria "+library.getId()+" ya existe.");
        }
        libraries.put(library.getId(), library);
    }
    
    public void addBookToALibrary(int id, Libro book) throws LibraryApiException {
        if (!libraries.containsKey(id)) {
            throw new LibraryApiException("La libreria "+id+" no existe.");
        }
        Libreria library = libraries.get(id);
        Map<Integer, Libro> books = library.getLibros();
        books.put(book.getId(), book);
        library.setLibros(books);
    }
    
    public void deleteLibrary(int id) throws LibraryApiException {
        Libreria library = getLibraryById(id);
        Map<Integer, Libro> books = library.getLibros();
        if (books.isEmpty()) {
            throw new LibraryApiException("La libreria '"+id+"' no puede ser eliminada, porque contiene libros.");
        }
        libraries.remove(id);
    }
    
    public Map<Integer, Libreria> getLibraries() {
        return libraries;
    }

    public void setLibraries(Map<Integer, Libreria> libraries) {
        this.libraries = libraries;
    }
}