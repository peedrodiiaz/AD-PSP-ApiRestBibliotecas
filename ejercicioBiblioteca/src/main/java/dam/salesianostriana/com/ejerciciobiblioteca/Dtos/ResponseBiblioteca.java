package dam.salesianostriana.com.ejerciciobiblioteca.Dtos;

import dam.salesianostriana.com.ejerciciobiblioteca.Model.Biblioteca;
import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseBiblioteca(
    @Schema(description = "Identificador único de la biblioteca" , example = "1")
    Long id,
    @Schema(description = "Nombre de la ciudad donde se encuentra la biblioteca" , example = "Sevilla")
    String nameCiudad,
    @Schema(description = "Nombre de la biblioteca" , example = "Biblioteca Central")
    String nameBiblioteca, 
    @Schema(description = "Año de fundación de la biblioteca" , example = "1990")
    int anioFundacion, 
    @Schema(description = "Número aproximado de libros en la biblioteca" , example = "50000")
    int numAproxLibros, 
    @Schema(description = "Descripción de la biblioteca" , example = "Biblioteca pública con una amplia colección de libros")
    String descripcion, 
    @Schema(description = "URL de la biblioteca" , example = "http://www.bibliotecacentral.com")
    String url

) {
    public static ResponseBiblioteca of(Biblioteca b){
        return new ResponseBiblioteca(
            b.getId(),
            b.getNameCiudad(),
            b.getNameBiblioteca(),
            b.getAnioFundacion(),
            b.getNumAproxLibros(),
            b.getDescripcion(),
            b.getUrl()
        );
    }

}
