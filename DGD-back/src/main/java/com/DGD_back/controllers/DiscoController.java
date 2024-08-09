package com.DGD_back.controllers;

import com.DGD_back.entities.Disco;
import com.DGD_back.services.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disco")
@CrossOrigin(value = "http://localhost:3000")
public class DiscoController {

    @Autowired
    DiscoService discoService;

    @GetMapping
    public ResponseEntity<List<Disco>> obtenerDiscos() {
        List<Disco> discos = discoService.obtenerDiscos();
        if (discos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(discos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disco> obtenerDiscoPorId(@PathVariable int id) {
        Disco disco = discoService.obtenerDiscoPorId(id);
        if (disco == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(disco);
    }

    @PostMapping()
    public ResponseEntity<Disco> guardarDisco(@RequestBody Disco disco) {
        Disco discoNuevo = discoService.guardarDisco(disco);
        return ResponseEntity.ok(discoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disco> actualizarDisco(@PathVariable("id") int id, @RequestBody Disco disco) {
        Disco discoActualizado = discoService.actualizarDisco(id, disco);
        if (discoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(discoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDisco(@PathVariable("id") int id) {
        boolean eliminado = discoService.eliminarDisco(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
