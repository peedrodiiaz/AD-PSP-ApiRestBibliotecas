package dam.salesianostriana.com.ejerciciobiblioteca.Controller;

import java.util.List;

import dam.salesianostriana.com.ejerciciobiblioteca.errors.GlobalErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dam.salesianostriana.com.ejerciciobiblioteca.Dtos.CreateBibliotecaCmd;
import dam.salesianostriana.com.ejerciciobiblioteca.Dtos.ResponseBiblioteca;
import dam.salesianostriana.com.ejerciciobiblioteca.Model.Biblioteca;
import dam.salesianostriana.com.ejerciciobiblioteca.Services.BibiliotecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/library/")
@RequiredArgsConstructor
@Tag(name="Biblioteca", description = "Controller de la clase Biblioteca")
public class BibliotecaController {
    
    private final BibiliotecaService bibiliotecaService;

    @GetMapping
    @Operation(summary = "EndPoint para obtener las bibliotecas")
    @ApiResponse(responseCode = "200", description = "Listado de bibliotecas obtenido con éxito",
        content = @Content(mediaType = "application/json",
        array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GlobalErrorController.class)),
        examples = @ExampleObject(
            value = """
                    [
                    {
                    "id": 1,
                    "nameCiudad": "Sevilla",
                    "nameBiblioteca": "Biblioteca Central",
                    "anioFundacion": 1990,
                    "numAproxLibros": 50000,
                    "descripcion": "Biblioteca pública con una amplia colección de libros",
                    "url": "http://www.bibliotecacentral.com"
                    },
                    {
                    "id": 2,
                    "nameCiudad": "Madrid",
                    "nameBiblioteca": "Biblioteca Nacional",
                    "anioFundacion": 1712,
                    "numAproxLibros": 3000000,
                    "descripcion": "La biblioteca más grande de España",
                    "url": "http://www.bibliotecanacional.es"
                    }
                    ]
                    """
        )      
        
    ))
    public List <ResponseBiblioteca> getAllBibliotecas() {
        return bibiliotecaService.getAll().stream().map(ResponseBiblioteca::of).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "EndPoint para obtener una biblioteca por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Biblioteca encontrada con éxito",
            content = @Content(mediaType = "application/json",
                schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseBiblioteca.class),
                examples = @ExampleObject(
                    value = """
                            {
                            "id": 1,
                            "nameCiudad": "Sevilla",
                            "nameBiblioteca": "Biblioteca Central",
                            "anioFundacion": 1990,
                            "numAproxLibros": 50000,
                            "descripcion": "Biblioteca pública con una amplia colección de libros",
                            "url": "http://www.bibliotecacentral.com"
                            }
                            """
                )
        )),
        @ApiResponse(responseCode = "404", description = "Biblioteca no encontrada",
            content = @Content(mediaType = "application/json",
            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GlobalErrorController.class),
            examples = @ExampleObject(
                value = """
                        {
                          "type": "https://example.com/probs/library-not-found",
                          "title": "Bibliotecas no encontrada",
                          "status": 404,
                          "detail": "La biblioteca con ID 10 no existe."
                        }
                        """
            )

        ))
    })
    public ResponseEntity <ResponseBiblioteca> getBibliotecaById(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBiblioteca.of(bibiliotecaService.getById(id)));
    }

    @PostMapping()
    @Operation(summary = "Crea una nueva biblioteca")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "EndPoint para crear una nueva biblioteca",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CreateBibliotecaCmd.class),
            examples = @ExampleObject(
                value = """
                        {
                        "nameCiudad": "Sevilla",
                        "nameBiblioteca": "Biblioteca Central",
                        "anioFundacion": 1990,
                        "numAproxLibros": 50000,
                        "descripcion": "Biblioteca pública con una amplia colección de libros",
                        "url": "http://www.bibliotecacentral.com"
                        }
                        """
            )
        )
    )
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201", description = "Biblioteca creada con éxito",
                content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseBiblioteca.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                "id": 1,
                                "nameCiudad": "Sevilla",
                                "nameBiblioteca": "Biblioteca Central",
                                "anioFundacion": 1990,
                                "numAproxLibros": 50000,
                                "descripcion": "Biblioteca pública con una amplia colección de libros",
                                "url": "http://www.bibliotecacentral.com"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(responseCode = "400", description = "Datos de la biblioteca no válidos",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GlobalErrorController.class),

                    examples = @ExampleObject(
                        value = """
                                {
                                  "type": "https://example.com/probs/invalid-library-data",
                                  "title": "Datos de biblioteca no válidos",
                                  "status": 400,
                                  "detail": "El nombre de la biblioteca no puede estar vacío."
                                }
                                """
                    )
                ))
        }
    )
    public ResponseEntity <ResponseBiblioteca> save(@RequestBody CreateBibliotecaCmd cmd){
        Biblioteca b = bibiliotecaService.save(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(ResponseBiblioteca.of(b));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una biblioteca existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Biblioteca actualizada con éxito",
            content = @Content(mediaType = "application/json",
                schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseBiblioteca.class),
                examples = @ExampleObject(
                    value = """
                            {
                            "id": 1,
                            "nameCiudad": "Sevilla",
                            "nameBiblioteca": "Biblioteca Central Actualizada",
                            "anioFundacion": 1990,
                            "numAproxLibros": 60000,
                            "descripcion": "Biblioteca pública con una amplia colección de libros y recursos digitales",
                            "url": "http://www.bibliotecacentral.com/actualizada"
                            }
                            """
                )
        )),
        @ApiResponse(responseCode = "404", description = "Biblioteca no encontrada",
        content = @Content(mediaType = "application/json",
            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GlobalErrorController.class),
            examples = @ExampleObject(
                value = """
                        {
                          "type": "https://example.com/probs/library-not-found",
                          "title": "Bibliotecas no encontrada",
                          "status": 404,
                          "detail": "La biblioteca con ID 10 no existe."
                        }
                        """
            )

        ))
    })
    public ResponseEntity <ResponseBiblioteca> edit(@PathVariable Long id, @RequestBody CreateBibliotecaCmd cmd){
        Biblioteca b = bibiliotecaService.edit(id, cmd);
        return ResponseEntity.ok(ResponseBiblioteca.of(b));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una biblioteca por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Biblioteca eliminada con éxito"),
        @ApiResponse(responseCode = "404", description = "Biblioteca no encontrada")
    })
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        bibiliotecaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
