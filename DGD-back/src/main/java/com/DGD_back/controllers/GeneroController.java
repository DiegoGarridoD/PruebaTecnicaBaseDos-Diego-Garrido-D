package com.DGD_back.controllers;

import com.DGD_back.entities.Genero;
import com.DGD_back.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
@CrossOrigin(value = "http://localhost:3000")
public class GeneroController {
    @Autowired
    GeneroService generoService;


    @GetMapping("/all")
    public ResponseEntity<List<Genero>> obtenerGeneros(){
        List<Genero> generos = generoService.obtenerGeneros();
        if(generos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(generos);
    }

    @GetMapping
    public ResponseEntity<Page<Genero>> obtenerGeneros(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        Pageable paging = PageRequest.of(page,size);
        Page<Genero> generos = generoService.obtenerGenerosPaginados(paging);

        if (generos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(generos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> obtenerGeneroPorId(@PathVariable("id") int id){
        Genero genero = generoService.obtenerGeneroPorId(id);
        if (genero == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genero);
    }

    @PostMapping()
    public ResponseEntity<Genero> guardarGenero(@RequestBody Genero genero){
        Genero generoNew = generoService.guardarGenero(genero);
        return ResponseEntity.ok(generoNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> actualizarGenero(@PathVariable("id") int id, @RequestBody Genero generoNuevo){
        Genero actualizado = generoService.actualizarGenero(id,generoNuevo);
        if (actualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGenero(@PathVariable("id") int id){
        boolean eliminado = generoService.eliminarGenero(id);
        if (!eliminado){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
