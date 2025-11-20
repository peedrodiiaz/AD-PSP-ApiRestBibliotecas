package dam.salesianostriana.com.ejerciciobiblioteca.Services;


import dam.salesianostriana.com.ejerciciobiblioteca.Dtos.CreateBibliotecaCmd;
import dam.salesianostriana.com.ejerciciobiblioteca.Model.Biblioteca;
import dam.salesianostriana.com.ejerciciobiblioteca.errors.InvalidLibraryDataException;
import dam.salesianostriana.com.ejerciciobiblioteca.errors.LibraryNotFoundException;
import org.springframework.stereotype.Service;

import dam.salesianostriana.com.ejerciciobiblioteca.Repositories.BibliotecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BibiliotecaService {

    private final BibliotecaRepository bibliotecaRepository;


    public List<Biblioteca> getAll(){
        List<Biblioteca>result = bibliotecaRepository.findAll();
        if (result.isEmpty()){
            throw  new LibraryNotFoundException();
        }
        return result;
    }
    public Biblioteca getById(Long id){
        return bibliotecaRepository.findById(id).
                orElseThrow(()-> new LibraryNotFoundException(id));
    }

    public Biblioteca save (CreateBibliotecaCmd cmd){
        if (!StringUtils.hasText(cmd.nameBiblioteca())){
            throw new InvalidLibraryDataException();
        }
        return bibliotecaRepository.save(cmd.toEntity());

    }
    public Biblioteca edit (Long id, CreateBibliotecaCmd cmd){

        return  bibliotecaRepository.findById(id)
                .map(b->{
                    b.setAnioFundacion(cmd.anioFundacion());
                    b.setNameBiblioteca(cmd.nameBiblioteca());
                    b.setDescripcion(cmd.descripcion());
                    b.setNameCiudad(cmd.nameCiudad());
                    b.setUrl(cmd.url());
                    b.setNumAproxLibros(cmd.numAproxLibros());
                    return bibliotecaRepository.save(b);
                })
                .orElseThrow(()-> new LibraryNotFoundException(id));
    }

    public void delete(Biblioteca b) {
        deleteById(b.getId());
    }

    public void deleteById(Long id) {
        bibliotecaRepository.deleteById(id);
    }




}
