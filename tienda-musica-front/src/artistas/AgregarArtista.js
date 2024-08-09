import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function AgregarArtista() {

    const navegacion = useNavigate();

    const [artista, setArtista] = useState({
        nombre: '',
        nacionalidad: ''
    });

    const {nombre, nacionalidad} = artista;

    const onInputChange = (e) => {
        setArtista({...artista, [e.target.name]: e.target.value});
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        const urlBase = "http://localhost:8080/artista";
        try {
            await axios.post(urlBase, artista);
            alert('Artista agregado con éxito');
            navegacion('/gestionartistas');
        } catch (error) {
            console.error('Error al agregar artista', error);
        }
    }


  return (
    <div classNameName='container'>

        <div classNameNameName='container text-center' style={{margin: "30px"}}>
            <h4>Agregar artista</h4>
        </div>

        <form onSubmit={(e) => onSubmit(e)}>
        <div className="mb-3">
            <label htmlFor="nombre" className="form-label">Nombre artista</label>
            <input type="text" className="form-control" id="nombre" name='nombre' required={true} value={nombre} onChange={(e) => onInputChange(e)}/>
        </div>
        <div className="mb-3">
            <label htmlFor="nacionalidad" className="form-label">Nacionalidad</label>
            <input type="text" className="form-control" id="nacionalidad" name='nacionalidad' required={true} value={nacionalidad} onChange={(e) => onInputChange(e)}/>
        </div>
        <div className='text-center'>
            <button type="submit" className="btn btn-warning btn-sm me-3">Agregar</button>
            <a href="/gestionartistas" className="btn btn-primary btn-sm">Volver</a>
        </div>
        
        </form>

    </div>
  )
}
