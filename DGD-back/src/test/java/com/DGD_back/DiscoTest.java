package com.DGD_back;

import com.DGD_back.entities.Artista;
import com.DGD_back.entities.Disco;
import com.DGD_back.entities.Genero;
import com.DGD_back.repositories.DiscoRepository;
import com.DGD_back.repositories.GeneroRepository;
import com.DGD_back.services.DiscoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiscoTest {

    @Mock
    private DiscoRepository discoRepository;

    @Mock
    private GeneroRepository generoRepository;

    @InjectMocks
    private DiscoService discoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerDiscos() {
        Genero genero = new Genero(1, "Rock");
        Artista artista = new Artista(1, "Artista1", "Nacionalidad1");
        Disco disco1 = new Disco(1, "Disco1", 10.0, 5, genero, artista);
        Disco disco2 = new Disco(2, "Disco2", 15.0, 3, genero, artista);
        List<Disco> discos = Arrays.asList(disco1, disco2);

        when(discoRepository.findAll()).thenReturn(discos);

        List<Disco> result = discoService.obtenerDiscos();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Disco1", result.get(0).getTitulo());
    }

    @Test
    void testObtenerDiscosPaginados() {
        Genero genero = new Genero(1, "Rock");
        Artista artista = new Artista(1, "Artista1", "Nacionalidad1");
        Disco disco1 = new Disco(1, "Disco1", 10.0, 5, genero, artista);
        Disco disco2 = new Disco(2, "Disco2", 15.0, 3, genero, artista);
        List<Disco> discos = Arrays.asList(disco1, disco2);
        Page<Disco> page = new PageImpl<>(discos, PageRequest.of(0, 10), 2);

        when(discoRepository.findAll(any(Pageable.class))).thenReturn(page);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Disco> result = discoService.obtenerDiscosPaginados(pageable);
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("Disco2", result.getContent().get(1).getTitulo());
    }

    @Test
    void testObtenerDiscosPorGeneroPaginados() {
        Genero genero = new Genero(1, "Rock");
        Artista artista = new Artista(1, "Artista1", "Nacionalidad1");
        Disco disco1 = new Disco(1, "Disco1", 10.0, 5, genero, artista);
        Disco disco2 = new Disco(2, "Disco2", 15.0, 3, genero, artista);
        List<Disco> discos = Arrays.asList(disco1, disco2);
        Page<Disco> page = new PageImpl<>(discos, PageRequest.of(0, 10), 2);

        when(generoRepository.findById(1)).thenReturn(Optional.of(genero));
        when(discoRepository.findByGenero(any(Genero.class), any(Pageable.class))).thenReturn(page);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Disco> result = discoService.obtenerDiscosPorGeneroPaginados(1, pageable);
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("Disco2", result.getContent().get(1).getTitulo());
    }

    @Test
    void testGuardarDisco() {
        Genero genero = new Genero(1, "Rock");
        Artista artista = new Artista(1, "Artista1", "Nacionalidad1");
        Disco disco = new Disco(1, "Disco1", 10.0, 5, genero, artista);

        when(discoRepository.save(any(Disco.class))).thenReturn(disco);

        Disco result = discoService.guardarDisco(disco);
        assertNotNull(result);
        assertEquals("Disco1", result.getTitulo());
    }

    @Test
    void testObtenerDiscoPorId() {
        Genero genero = new Genero(1, "Rock");
        Artista artista = new Artista(1, "Artista1", "Nacionalidad1");
        Disco disco = new Disco(1, "Disco1", 10.0, 5, genero, artista);

        when(discoRepository.findById(1)).thenReturn(Optional.of(disco));

        Disco result = discoService.obtenerDiscoPorId(1);
        assertNotNull(result);
        assertEquals("Disco1", result.getTitulo());
    }

    @Test
    void testEliminarDisco() {
        doNothing().when(discoRepository).deleteById(anyInt());

        boolean result = discoService.eliminarDisco(1);
        assertTrue(result);
        verify(discoRepository, times(1)).deleteById(1);
    }

    @Test
    void testActualizarDisco() {
        Genero genero = new Genero(1, "Rock");
        Artista artista = new Artista(1, "Artista1", "Nacionalidad1");
        Disco discoExistente = new Disco(1, "Disco1", 10.0, 5, genero, artista);
        Disco discoNuevo = new Disco(1, "Disco Actualizado", 12.0, 4, genero, artista);

        when(discoRepository.findById(1)).thenReturn(Optional.of(discoExistente));
        when(discoRepository.save(any(Disco.class))).thenReturn(discoNuevo);

        Disco result = discoService.actualizarDisco(1, discoNuevo);
        assertNotNull(result);
        assertEquals("Disco Actualizado", result.getTitulo());
    }
}
