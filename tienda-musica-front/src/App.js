import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navegacion from "./plantilla/Navegacion";
import GestionArtistas from "./artistas/GestionArtistas";
import AgregarArtista from "./artistas/AgregarArtista";
import GestionDiscos from "./discos/GestionDiscos";
import EditarArtista from "./artistas/EditarArtista";
import GestionGeneros from "./generos/GestionGeneros";
import AgregarGenero from "./generos/AgregarGenero";
import EditarGenero from "./generos/EditarGenero";
import AgregarDisco from "./discos/AgregarDisco";
import EditarDisco from "./discos/EditarDisco";

function App() {
  return (
    <div className="container">
      <BrowserRouter>
        <Navegacion />
        <Routes>
          <Route path="/" element={<GestionDiscos />} />{/* Reemplazar por discos */}
          <Route path="/gestionartistas" element={<GestionArtistas />} />
          <Route path="/agregarartista" element={<AgregarArtista />} />
          <Route path="/editarartista/:id" element={<EditarArtista />} />
          <Route path="/gestiongeneros" element={<GestionGeneros/>} />
          <Route path="/agregargenero" element={<AgregarGenero/>} />
          <Route path="/editargenero/:id" element={<EditarGenero />} />
          <Route path="/gestiondiscos" element={<GestionDiscos />} />
          <Route path="/agregardisco" element={<AgregarDisco />} />
          <Route path="/editardisco/:id" element={<EditarDisco />} />
          

        </Routes>
      </BrowserRouter>
      
    </div>
  );
}

export default App;

