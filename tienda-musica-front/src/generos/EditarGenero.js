import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

export default function EditarGenero() {
    const urlBase = "http://localhost:8080/genero";

    let navegacion = useNavigate();

    const {id} = useParams();

    const [genero, setGenero] = useState({
        nombre: ''
    });

    const {nombre} = genero;

    useEffect(() => {
        cargarGenero();
    }, []);

    const cargarGenero = async () => {
        const resultado = await axios.get(`${urlBase}/${id}`);
        setGenero(resultado.data);
    }

    const onInputChange = (e) => {
        setGenero({...genero, [e.target.name]: e.target.value});
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.put(`${urlBase}/${id}`, genero);
        navegacion('/gestiongeneros');
    }


  return (
    <div className='container'>
        <div classNameNameName='container text-center' style={{margin: "30px"}}>
            <h4>Editar género</h4>
        </div>

        <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
                <label htmlFor="nombre" className="form-label">Nombre género</label>
                <input type="text" className="form-control" id="nombre" name='nombre' required={true} value={nombre} onChange={(e) => onInputChange(e)}/>
            </div>
            <div className='text-center'>
                <button type="submit" className="btn btn-warning btn-sm me-3">Guardar</button>
                <a href="/gestiongeneros" className="btn btn-primary btn-sm">Volver</a>
            </div>
        </form>

    </div>
  )
}
