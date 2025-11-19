package dam.salesianostriana.com.ejerciciobiblioteca.errors;

public class InvalidLibraryDataException extends  RuntimeException{
    public InvalidLibraryDataException(){
        super( "Los datos de la biblioteca no son válidos");
    }
}
