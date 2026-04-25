package edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.servicio;

import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity.LineaCarrito;
import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.repositorios.RepoCarrito;
import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.repositorios.RepoLineaCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service

public class ServicioCarrito {

    @Autowired
    RepoLineaCarrito repoLineaCarrito;
    @Autowired
    RepoCarrito repoCarrito;

    //crearme un carito
    @Transactional
    public Carrito crea(Carrito nuevoCarrito) {

        if (repoCarrito.findByCorreo(nuevoCarrito.correo) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un carrito con ese correo");
        }

        if (repoCarrito.findByIdUsuario(nuevoCarrito.idUsuario) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un carrito para ese usuario");
        }

        return repoCarrito.save(nuevoCarrito);
    }

    @Transactional
    public Carrito lee( Long idCarrito){

        Carrito carrito = repoCarrito.findByIdCarrito(idCarrito);
        if (carrito == null ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado ");

        }
        return carrito;

    }

    @Transactional
    public void borra(Long idCarrito) {

        Carrito carrito = repoCarrito.findByIdCarrito(idCarrito);
        repoCarrito.delete(carrito);
    }

    //lineas

    //añadir lineas al carrito(añador producto al carrito)

    public Carrito crearLinea( LineaCarrito linea , Long idCarrito){

        //encuentro el carrito al que le quiero guardar la linea
        Carrito carrito = lee(idCarrito);

        //llamo a una funcion que me alada los datos a la lista
        carrito.nuevaLinea(linea);

        return repoCarrito.save(carrito);
    }

    //borrar linea

    @Transactional
    public Carrito borrarLinea(Long idCarrito, Long idArticulo) {

        Carrito carrito = lee(idCarrito);

        LineaCarrito lineaAEliminar = null;

        for (LineaCarrito linea : carrito.getLineas()) {
            if (linea.idArticulo != null && linea.idArticulo.equals(idArticulo)) {
                lineaAEliminar = linea;
                break;
            }
        }

        if (lineaAEliminar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Línea no encontrada");
        }

        carrito.borrarLinea(lineaAEliminar);

        return repoCarrito.save(carrito);
    }

}
