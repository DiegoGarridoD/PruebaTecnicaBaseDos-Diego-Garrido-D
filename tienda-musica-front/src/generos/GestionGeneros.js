import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

export default function GestionGeneros() {
    const urlBase = "http://localhost:8080/genero";

    const[generos, setGeneros] = useState([]);
    const[paginaActual, setPaginaActual] = useState(0);
    const[totalPaginas, setTotalPaginas] = useState(0);

    useEffect(() => {
        cargarGeneros(paginaActual);
    }
    , [paginaActual]);

    const cargarGeneros = async (pagina = 0) => {
        const resultado = await axios.get(`${urlBase}?page=${pagina}&size=10`);
        setGeneros(resultado.data.content);
        setTotalPaginas(resultado.data.totalPages);
    }

    const eliminarGenero = async (id) => {
      console.log(id);  // Verifica el ID
      await axios.delete(`${urlBase}/${id}`);
      cargarGeneros();
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
                <h4 className="mb-0">Generos disponibles</h4>
                <Link to="/agregargenero" className="btn btn-success btn-sm">Agregar genero</Link>
            </div>
            
            <table className="table table-striped table-hover align-middle">
                <thead className='table-dark'>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {
                    
                    generos.map(genero => (
                        <tr key={genero.id}>
                            <th scope="row">{genero.id}</th>
                            <td>{genero.nombre}</td>
                            <td className='text-center'>
                                <div>
                                    <Link to={`/editargenero/${genero.id}`} className="btn btn-warning btn-sm me-3">Editar</Link>
                                    <button onClick={() => eliminarGenero(genero.id)} className="btn btn-danger btn-sm">Eliminar</button>
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
