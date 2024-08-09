import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function AgregarGenero() {

    let navegacion = useNavigate();

    const [genero, setGenero] = useState({
        nombre: ''
    });

    const {nombre} = genero;

    const onInputChange = (e) => {
        setGenero({...genero, [e.target.name]: e.target.value});
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        const urlBase = "http://localhost:8080/genero";
        await axios.post(urlBase, genero);
        navegacion('/gestiongeneros');
    }


  return (
    <div className='container'>
        <div classNameNameName='container text-center' style={{margin: "30px"}}>
            <h4>Agregar género</h4>
        </div>

        <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
                <label htmlFor="nombre" className="form-label">Nombre género</label>
                <input type="text" className="form-control" id="nombre" name='nombre' required={true} value={nombre} onChange={(e) => onInputChange(e)}/>
            </div>
            <div className='text-center'>
                <button type="submit" className="btn btn-warning btn-sm me-3">Agregar</button>
                <a href="/gestiongeneros" className="btn btn-primary btn-sm">Volver</a>
            </div>
        
        </form>

    </div>
  )
}
