package dam.salesianostriana.com.ejerciciobiblioteca.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler{
    @ExceptionHandler(LibraryNotFoundException.class)
    public ProblemDetail handleBibliotecaNotFoundException(LibraryNotFoundException ex){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Bibliotecas no encontrada");
        return pd;
    }

    @ExceptionHandler(InvalidLibraryDataException.class)
    public ProblemDetail handleInvalidLibraryDataException(InvalidLibraryDataException ex){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("Datos de biblioteca no válidos");
        return pd;
    }
}