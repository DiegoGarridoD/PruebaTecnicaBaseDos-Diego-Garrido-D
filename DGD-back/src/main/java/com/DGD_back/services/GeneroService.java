package com.DGD_back.services;

import com.DGD_back.entities.Genero;
import com.DGD_back.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {
    @Autowired
    GeneroRepository generoRepository;

    public List<Genero> obtenerGeneros(){return generoRepository.findAll();}

    public Page<Genero> obtenerGenerosPaginados(Pageable pageable){return generoRepository.findAll(pageable);}

    public Genero obtenerGeneroPorId(int id){
        return generoRepository.findById(id).orElse(null);}

    public Genero guardarGenero(Genero genero){
        Genero generoNew = generoRepository.save(genero);
        return generoNew;
    }

    public boolean eliminarGenero(int id) {
        Optional<Genero> genero = generoRepository.findById(id);
        if (genero.isPresent()) {
            generoRepository.delete(genero.get());
            return true;
        }
        return false;
    }


    public Genero actualizarGenero(int id, Genero nuevoGenero){
        try {
            Genero generoExistente = generoRepository.findById(id).orElse(null);
            if (generoExistente != null){
                generoExistente.setNombre(nuevoGenero.getNombre());
                return generoRepository.save(generoExistente);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }
}
