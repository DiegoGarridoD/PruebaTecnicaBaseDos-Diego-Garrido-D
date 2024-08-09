package com.DGD_back;

import com.DGD_back.entities.Genero;
import com.DGD_back.repositories.GeneroRepository;
import com.DGD_back.services.GeneroService;
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

public class GeneroTest {

    @Mock
    private GeneroRepository generoRepository;

    @InjectMocks
    private GeneroService generoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerGeneros() {
        Genero genero1 = new Genero(1, "Rock");
        Genero genero2 = new Genero(2, "Pop");
        List<Genero> generos = Arrays.asList(genero1, genero2);

        when(generoRepository.findAll()).thenReturn(generos);

        List<Genero> result = generoService.obtenerGeneros();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Rock", result.get(0).getNombre());
    }

    @Test
    void testObtenerGenerosPaginados() {
        Genero genero1 = new Genero(1, "Rock");
        Genero genero2 = new Genero(2, "Pop");
        List<Genero> generos = Arrays.asList(genero1, genero2);
        Page<Genero> page = new PageImpl<>(generos, PageRequest.of(0, 10), 2);

        when(generoRepository.findAll(any(Pageable.class))).thenReturn(page);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Genero> result = generoService.obtenerGenerosPaginados(pageable);
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("Pop", result.getContent().get(1).getNombre());
    }

    @Test
    void testObtenerGeneroPorId() {
        Genero genero = new Genero(1, "Rock");

        when(generoRepository.findById(1)).thenReturn(Optional.of(genero));

        Genero result = generoService.obtenerGeneroPorId(1);
        assertNotNull(result);
        assertEquals("Rock", result.getNombre());
    }

    @Test
    void testGuardarGenero() {
        Genero genero = new Genero(1, "Rock");

        when(generoRepository.save(any(Genero.class))).thenReturn(genero);

        Genero result = generoService.guardarGenero(genero);
        assertNotNull(result);
        assertEquals("Rock", result.getNombre());
    }

    @Test
    void testEliminarGenero() {
        Genero genero = new Genero(1, "Rock");

        when(generoRepository.findById(1)).thenReturn(Optional.of(genero));
        doNothing().when(generoRepository).delete(any(Genero.class));

        boolean result = generoService.eliminarGenero(1);
        assertTrue(result);
        verify(generoRepository, times(1)).delete(genero);
    }

    @Test
    void testActualizarGenero() {
        Genero generoExistente = new Genero(1, "Rock");
        Genero generoNuevo = new Genero(1, "Jazz");

        when(generoRepository.findById(1)).thenReturn(Optional.of(generoExistente));
        when(generoRepository.save(any(Genero.class))).thenReturn(generoNuevo);

        Genero result = generoService.actualizarGenero(1, generoNuevo);
        assertNotNull(result);
        assertEquals("Jazz", result.getNombre());
    }
}
