package com.DGD_back.controllers;


import com.DGD_back.entities.Artista;
import com.DGD_back.services.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artista")
@CrossOrigin(value = "http://localhost:3000")
public class ArtistaController {

    @Autowired
    ArtistaService artistaService;


    @GetMapping("/all")
    public ResponseEntity<List<Artista>> obtenerArtistas(){
        List<Artista> artistas = artistaService.obtenerArtistas();
        if (artistas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(artistas);
    }

    @GetMapping
    public ResponseEntity<Page<Artista>> obtenerArtistas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Artista> artistas = artistaService.obtenerArtistasPaginados(paging);

        if (artistas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(artistas);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Artista> obtenerArtistaPorId(@PathVariable("id") int id) {
        Artista artista = artistaService.obtenerArtistaPorId(id);
        if (artista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artista);
    }

    @PostMapping()
    public ResponseEntity<Artista> guardarArtista(@RequestBody Artista artista) {
        Artista artistaNuevo = artistaService.guardarArtista(artista);
        return ResponseEntity.ok(artistaNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> actualizarArtista(@PathVariable("id") int id, @RequestBody Artista artista) {
        Artista artistaActualizado = artistaService.actualizarArtista(id, artista);
        if (artistaActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artistaActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable("id") int id) {
        boolean eliminado = artistaService.eliminarArtista(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
