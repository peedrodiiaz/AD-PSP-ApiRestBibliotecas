package dam.salesianostriana.com.ejerciciobiblioteca.errors;

public class LibraryNotFoundException extends RuntimeException {
    

    public LibraryNotFoundException(){
        super("No se han encontrado bibliotecas");
    }
    public LibraryNotFoundException(Long id ){
        super("No se ha encontrado la biblioteca con el id " +id);
    }


}
