package com.DGD_back.services;

import com.DGD_back.entities.Artista;
import com.DGD_back.entities.Genero;
import com.DGD_back.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {
    @Autowired
    ArtistaRepository artistaRepository;

    public List<Artista> obtenerArtistas(){return artistaRepository.findAll();}

    public Artista obtenerArtistaPorId(int id){
        return artistaRepository.findById(id).orElse(null);}

    public Artista guardarArtista(Artista artista){
        Artista artistaNew = artistaRepository.save(artista);
        return artistaNew;
    }

    public boolean eliminarArtista(int id){
        try {
            artistaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Artista actualizarArtista(int id, Artista nuevoArtista){
        try {
            Artista artistaExistente = artistaRepository.findById(id).orElse(null);
            if (artistaExistente != null){
                //Actualizar
                artistaExistente.setNombre(nuevoArtista.getNombre());
                artistaExistente.setNacionalidad(nuevoArtista.getNacionalidad());
                return artistaRepository.save(artistaExistente);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }
}
