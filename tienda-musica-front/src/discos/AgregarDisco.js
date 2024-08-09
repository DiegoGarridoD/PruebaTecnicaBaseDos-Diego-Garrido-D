import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export default function AgregarDisco() {
    const urlBaseDisco = "http://localhost:8080/disco";
    const urlBaseGeneros = "http://localhost:8080/genero/all";
    const urlBaseArtistas = "http://localhost:8080/artista/all";

    const [titulo, setTitulo] = useState('');
    const [precio, setPrecio] = useState('');
    const [existencias, setExistencias] = useState('');
    const [generoId, setGeneroId] = useState('');
    const [artistaId, setArtistaId] = useState('');
    const [generos, setGeneros] = useState([]);
    const [artistas, setArtistas] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        const fetchGeneros = async () => {
            try {
                const resultadoGeneros = await axios.get(urlBaseGeneros);
                setGeneros(Array.isArray(resultadoGeneros.data) ? resultadoGeneros.data : []);
            } catch (error) {
                console.error("Error al cargar los géneros", error);
            }
        };

        const fetchArtistas = async () => {
            try {
                const resultadoArtistas = await axios.get(urlBaseArtistas);
                setArtistas(Array.isArray(resultadoArtistas.data) ? resultadoArtistas.data : []);
            } catch (error) {
                console.error("Error al cargar los artistas", error);
            }
        };

        fetchGeneros();
        fetchArtistas();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const disco = {
            titulo,
            precio: parseFloat(precio),
            existencias: parseInt(existencias),
            genero: { id: parseInt(generoId) },
            artista: { id: parseInt(artistaId) },
        };

        try {
            await axios.post(urlBaseDisco, disco);
            alert('Disco agregado con éxito');
            navigate('/'); 
        } catch (error) {
            console.error('Hubo un error al agregar el disco', error);
        }
    };

    return (
        <div className="container">
            <h3>Agregar Disco</h3>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Título</label>
                    <input
                        type="text"
                        className="form-control"
                        value={titulo}
                        onChange={(e) => setTitulo(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Precio</label>
                    <input
                        type="number"
                        className="form-control"
                        value={precio}
                        onChange={(e) => setPrecio(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Existencias</label>
                    <input
                        type="number"
                        className="form-control"
                        value={existencias}
                        onChange={(e) => setExistencias(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Género</label>
                    <select
                        className="form-select"
                        value={generoId}
                        onChange={(e) => setGeneroId(e.target.value)}
                        required
                    >
                        <option value="">Selecciona un género</option>
                        {generos.map((genero) => (
                            <option key={genero.id} value={genero.id}>
                                {genero.nombre}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="mb-3">
                    <label className="form-label">Artista</label>
                    <select
                        className="form-select"
                        value={artistaId}
                        onChange={(e) => setArtistaId(e.target.value)}
                        required
                    >
                        <option value="">Selecciona un artista</option>
                        {artistas.map((artista) => (
                            <option key={artista.id} value={artista.id}>
                                {artista.nombre}
                            </option>
                        ))}
                    </select>
                </div>

                <div className='text-center'>
                    <button type="submit" className="btn btn-warning btn-sm me-3">Agregar</button>
                    <a href="/gestiongeneros" className="btn btn-primary btn-sm">Volver</a>
                </div>
        
            </form>
        </div>
    );
}
