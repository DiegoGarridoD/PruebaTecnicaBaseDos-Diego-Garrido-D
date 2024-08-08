package com.DGD_back.services;

import com.DGD_back.entities.Disco;
import com.DGD_back.repositories.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoService {
    @Autowired
    DiscoRepository discoRepository;

    public List<Disco> obtenerDiscos() {
        return discoRepository.findAll();
    }

    public Disco guardarDisco(Disco disco) {
        return discoRepository.save(disco);
    }

    public Disco obtenerDiscoPorId(int id) {
        return discoRepository.findById(id).orElse(null);
    }

    public boolean eliminarDisco(int id) {
        try {
            discoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Disco actualizarDisco(int id, Disco disco) {
        try {
            Disco discoExistente = discoRepository.findById(id).orElse(null);
            if (discoExistente != null) {
                discoExistente.setTitulo(disco.getTitulo());
                discoExistente.setPrecio(disco.getPrecio());
                discoExistente.setExistencias(disco.getExistencias());
                discoExistente.setGenero(disco.getGenero());
                discoExistente.setArtista(disco.getArtista());
                return discoRepository.save(discoExistente);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
