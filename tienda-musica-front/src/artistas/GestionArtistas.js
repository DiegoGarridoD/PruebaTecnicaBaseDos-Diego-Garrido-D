import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

export default function GestionArtistas() {
    const urlBase = "http://localhost:8080/artista";

    const[artistas, setArtistas] = useState([]);
    const[paginaActual, setPaginaActual] = useState(0);
    const[totalPaginas, setTotalPaginas] = useState(0);

    useEffect(() => {
        cargarArtistas(paginaActual);
    }
    , [paginaActual]);

    const cargarArtistas = async (pagina = 0) => {
        const resultado = await axios.get(`${urlBase}?page=${pagina}&size=10`);
        setArtistas(resultado.data.content);
        setTotalPaginas(resultado.data.totalPages);
    }

    const eliminarArtista = async (id) => {
        await axios.delete(`${urlBase}/${id}`);
        cargarArtistas();
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

  return (
    <div className='container'> 
    
        <div className="container text-center" style={{margin: "30px"}}>
            <div className="d-flex justify-content-between align-items-center">
                <h4 className="mb-0">Artistas disponibles</h4>
                <Link to="/agregarartista" className="btn btn-success btn-sm">Agregar artista</Link>
            </div>
            
            <table className="table table-striped table-hover align-middle">
                <thead className='table-dark'>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Nacionalidad</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {//Se itera arreglo de artistas para mostrarlos en la tabla, al final en nacionalidad dejar un boton a la derecha para editar 
                    
                    artistas.map(artista => (
                        <tr key={artista.id}>
                            <th scope="row">{artista.id}</th>
                            <td>{artista.nombre}</td>
                            <td>{artista.nacionalidad}</td>
                            <td className='text-center'>
                                <div>
                                    <Link to={`/editarartista/${artista.id}`} className="btn btn-warning btn-sm me-3">Editar</Link>
                                    <button onClick={() => eliminarArtista(artista.id)} className="btn btn-danger btn-sm">Eliminar</button>
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
    

    
    
  )
}
