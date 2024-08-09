import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

export default function GestionDiscos() {
    const urlBaseDisco = "http://localhost:8080/disco";
    const urlBaseGeneros = "http://localhost:8080/genero/all";

    const [discos, setDiscos] = useState([]);
    const [paginaActual, setPaginaActual] = useState(0);
    const [totalPaginas, setTotalPaginas] = useState(0);
    const [generos, setGeneros] = useState([]);
    const [generoSeleccionado, setGeneroSeleccionado] = useState('');

    useEffect(() => {
        const fetchGeneros = async () => {
            try {
                const resultadoGeneros = await axios.get(urlBaseGeneros);
                setGeneros(Array.isArray(resultadoGeneros.data) ? resultadoGeneros.data : []);
            } catch (error) {
                console.error("Error al cargar los géneros", error);
            }
        };

        fetchGeneros();
    }, []);

    useEffect(() => {
        cargarDiscos(paginaActual, generoSeleccionado);
    }, [paginaActual, generoSeleccionado]);

    const cargarDiscos = async (pagina = 0, generoId = '') => {
        const url = generoId ? `${urlBaseDisco}/all/${generoId}?page=${pagina}&size=10` : `${urlBaseDisco}?page=${pagina}&size=10`;
        try {
            const resultado = await axios.get(url);
            if (resultado.data.content.length === 0 && generoId) {
                alert('No hay discos para el género seleccionado');
                setGeneroSeleccionado(''); // Reinicia el filtro
                const resultadoTodos = await axios.get(`${urlBaseDisco}?page=${pagina}&size=10`); // Obtiene todos los discos
                setDiscos(resultadoTodos.data.content);
                setTotalPaginas(resultadoTodos.data.totalPages);
            } else {
                setDiscos(resultado.data.content);
                setTotalPaginas(resultado.data.totalPages);
            }
        } catch (error) {
            console.error('Error al cargar los discos', error);
        }
    }

    const eliminarDisco = async (id) => {
        await axios.delete(`${urlBaseDisco}/${id}`);
        cargarDiscos(paginaActual, generoSeleccionado);
    }

    const handlePaginaAnterior = () => {
        if (paginaActual > 0) {
            setPaginaActual(paginaActual - 1);
        }
    };

    const handlePaginaSiguiente = () => {
        if (paginaActual < totalPaginas - 1) {
            setPaginaActual(paginaActual + 1);
        }
    }

    const actualizarExistencias = async (id, nuevaCantidad) => {
        const disco = discos.find(d => d.id === id);
        if (disco) {
            const discoActualizado = {
                ...disco,
                existencias: nuevaCantidad
            };

            try {
                await axios.put(`${urlBaseDisco}/${id}`, discoActualizado);
                cargarDiscos(paginaActual, generoSeleccionado);
            } catch (error) {
                console.error('Error al actualizar las existencias', error);
            }
        }
    }

    const incrementarExistencias = (id, cantidadActual) => {
        actualizarExistencias(id, cantidadActual + 1);
    }

    const decrementarExistencias = (id, cantidadActual) => {
        if (cantidadActual > 0) { // Evita restar si las existencias son 0
            actualizarExistencias(id, cantidadActual - 1);
        }
    }

    const handleFiltroGenero = async (e) => {
        const idGenero = e.target.value;
        setGeneroSeleccionado(idGenero);
        setPaginaActual(0); // Reinicia la página actual al cambiar el filtro
        await cargarDiscos(0, idGenero);
    }

    return (
        <div className='container'>
            <div className='container text-center' style={{ margin: "30px" }}>
                <div className="d-flex justify-content-between align-items-center mb-3">
                    <h4 className="mb-0">Discos disponibles</h4>
                    <Link to="/agregardisco" className="btn btn-success btn-sm">Agregar disco</Link>
                </div>

                <div className="mb-3">
                    <label className="form-label">Filtrar por género</label>
                    <select
                        className="form-select"
                        value={generoSeleccionado}
                        onChange={handleFiltroGenero}
                    >
                        <option value="">Todos los géneros</option>
                        {generos.map((genero) => (
                            <option key={genero.id} value={genero.id}>
                                {genero.nombre}
                            </option>
                        ))}
                    </select>
                </div>

                <table className="table table-striped table-hover align-middle">
                    <thead className='table-dark'>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Título</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Existencias</th>
                            <th scope="col">Género</th>
                            <th scope="col">Artista</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        {discos.map(disco => (
                            <tr key={disco.id}>
                                <th scope="row">{disco.id}</th>
                                <td>{disco.titulo}</td>
                                <td>{disco.precio}</td>
                                <td>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <button
                                            className="btn btn-outline-danger btn-sm me-2"
                                            onClick={() => decrementarExistencias(disco.id, disco.existencias)}
                                        >
                                            -
                                        </button>
                                        <span>{disco.existencias}</span>
                                        <button
                                            className="btn btn-outline-success btn-sm ms-2"
                                            onClick={() => incrementarExistencias(disco.id, disco.existencias)}
                                        >
                                            +
                                        </button>
                                    </div>
                                </td>
                                <td>{disco.genero.nombre}</td> {/* Muestra el nombre del género */}
                                <td>{disco.artista.nombre}</td> {/* Muestra el nombre del artista */}
                                <td className='text-center'>
                                    <div>
                                        <Link to={`/editardisco/${disco.id}`} className="btn btn-warning btn-sm me-3">Editar</Link>
                                        <button onClick={() => eliminarDisco(disco.id)} className="btn btn-danger btn-sm">Eliminar</button>
                                    </div>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <div className="d-flex justify-content-between">
                    <button
                        className="btn btn-secondary"
                        onClick={handlePaginaAnterior}
                        disabled={paginaActual === 0}>
                        Anterior
                    </button>
                    <button
                        className="btn btn-secondary"
                        onClick={handlePaginaSiguiente}
                        disabled={paginaActual === totalPaginas - 1}>
                        Siguiente
                    </button>
                </div>
            </div>
        </div>
    );
}
