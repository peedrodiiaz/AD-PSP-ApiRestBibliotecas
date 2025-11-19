package dam.salesianostriana.com.ejerciciobiblioteca.Model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Biblioteca {

    @Id
    @GeneratedValue
    private Long id;
    private String nameCiudad;
    private String nameBiblioteca;
    private int anioFundacion;
    private int numAproxLibros;
    private String descripcion;
    private String url;

}
