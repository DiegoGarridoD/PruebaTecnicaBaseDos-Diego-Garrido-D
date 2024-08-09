package com.DGD_back;

import com.DGD_back.entities.Artista;
import com.DGD_back.repositories.ArtistaRepository;
import com.DGD_back.services.ArtistaService;
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

public class ArtistaTest {

    @Mock
    private ArtistaRepository artistaRepository;

    @InjectMocks
    private ArtistaService artistaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerArtistas() {
        Artista artista1 = new Artista(1, "Artista1", "Nacionalidad1");
        Artista artista2 = new Artista(2, "Artista2", "Nacionalidad2");
        List<Artista> artistas = Arrays.asList(artista1, artista2);

        when(artistaRepository.findAll()).thenReturn(artistas);

        List<Artista> result = artistaService.obtenerArtistas();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Artista1", result.get(0).getNombre());
    }

    @Test
    void testObtenerArtistasPaginados() {
        Artista artista1 = new Artista(1, "Artista1", "Nacionalidad1");
        Artista artista2 = new Artista(2, "Artista2", "Nacionalidad2");
        List<Artista> artistas = Arrays.asList(artista1, artista2);
        Page<Artista> page = new PageImpl<>(artistas, PageRequest.of(0, 10), 2);

        when(artistaRepository.findAll(any(Pageable.class))).thenReturn(page);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Artista> result = artistaService.obtenerArtistasPaginados(pageable);
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("Artista2", result.getContent().get(1).getNombre());
    }

    @Test
    void testObtenerArtistaPorId() {
        Artista artista = new Artista(1, "Artista1", "Nacionalidad1");

        when(artistaRepository.findById(1)).thenReturn(Optional.of(artista));

        Artista result = artistaService.obtenerArtistaPorId(1);
        assertNotNull(result);
        assertEquals("Artista1", result.getNombre());
    }

    @Test
    void testGuardarArtista() {
        Artista artista = new Artista(1, "Artista1", "Nacionalidad1");

        when(artistaRepository.save(any(Artista.class))).thenReturn(artista);

        Artista result = artistaService.guardarArtista(artista);
        assertNotNull(result);
        assertEquals("Artista1", result.getNombre());
    }

    @Test
    void testEliminarArtista() {
        Artista artista = new Artista(1, "Artista1", "Nacionalidad1");

        when(artistaRepository.findById(1)).thenReturn(Optional.of(artista));
        doNothing().when(artistaRepository).delete(any(Artista.class));

        boolean result = artistaService.eliminarArtista(1);
        assertTrue(result);
        verify(artistaRepository, times(1)).delete(artista);
    }

    @Test
    void testActualizarArtista() {
        Artista artistaExistente = new Artista(1, "Artista1", "Nacionalidad1");
        Artista artistaNuevo = new Artista(1, "Artista Actualizado", "Nacionalidad Actualizada");

        when(artistaRepository.findById(1)).thenReturn(Optional.of(artistaExistente));
        when(artistaRepository.save(any(Artista.class))).thenReturn(artistaNuevo);

        Artista result = artistaService.actualizarArtista(1, artistaNuevo);
        assertNotNull(result);
        assertEquals("Artista Actualizado", result.getNombre());
    }
}
