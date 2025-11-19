package dam.salesianostriana.com.ejerciciobiblioteca.Dtos;

import dam.salesianostriana.com.ejerciciobiblioteca.Model.Biblioteca;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateBibliotecaCmd(
    @Schema(description = "Nombre de la ciudad donde se encuentra la biblioteca" , example = "Sevilla")
    String nameCiudad
    , @Schema(description = "Nombre de la biblioteca" , example = "Biblioteca Central")
    String nameBiblioteca
    , @Schema(description = "Año de fundación de la biblioteca" , example = "1990")
    int anioFundacion
    , @Schema(description = "Número aproximado de libros en la biblioteca" , example = "50000")
    int numAproxLibros
    , @Schema(description = "Descripción de la biblioteca" , example = "Biblioteca pública con una amplia colección de libros")
    String descripcion
    , @Schema(description = "URL de la biblioteca" , example = "http://www.bibliotecacentral.com")
    String url
) {
    public Biblioteca toEntity(){
        return Biblioteca.builder()
        .nameCiudad(this.nameCiudad)
        .nameBiblioteca(this.nameBiblioteca)
        .anioFundacion(this.anioFundacion)
        .numAproxLibros(this.numAproxLibros)
        .descripcion(this.descripcion)
        .url(this.url)        
        .build();
    }
}
