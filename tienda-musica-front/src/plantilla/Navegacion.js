import React from 'react'

export default function Navegacion() {
  return (
    <div classNameNameName='container'>

        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
        <div className="container-fluid">
            <a className="navbar-brand" href="/">Tienda de Musica</a>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div className="navbar-nav">
                <a className="nav-link active" aria-current="page" href="/">Inicio</a>
                <a className="nav-link" href="/gestionartistas">Gestión Artistas</a>
                <a className="nav-link" href="/gestiongeneros">Gestión Genero</a>
            </div>
            </div>
        </div>
        </nav>

    </div>
  )
}
