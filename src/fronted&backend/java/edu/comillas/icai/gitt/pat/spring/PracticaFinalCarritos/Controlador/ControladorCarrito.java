package edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.Controlador;

import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity.LineaCarrito;
import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.repositorios.RepoCarrito;
import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.servicio.ServicioCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorCarrito {


    @Autowired
    RepoCarrito repoCarrito;
    @Autowired
    ServicioCarrito servicioCarrito;

    @PostMapping("/api/carrito") @ResponseStatus(HttpStatus.CREATED)
    public Carrito crea(@RequestBody Carrito NuevoCarrito) {

        Carrito carrito= servicioCarrito.crea(NuevoCarrito );
        return carrito;
    }

    @GetMapping("/api/carrito/{Id}")
    public Carrito lee(@PathVariable Long Id) {
        return servicioCarrito.lee(Id);

    }


    @DeleteMapping("/api/carrito/{Id}")
    public void borra(@PathVariable Long Id) {
        servicioCarrito.borra(Id);
    }


    //añadir linea

    @PostMapping("/api/carrito/{Id}/linea")
    public Carrito anadirLinea(@RequestBody LineaCarrito lineaCarrito, @PathVariable Long Id) {
        Carrito carrito = servicioCarrito.crearLinea(lineaCarrito,Id);
        return carrito;
    }

    //borrar linea
    @DeleteMapping("/api/carrito/{Id}/linea/{idArticulo}")
    public Carrito borrarLinea(@PathVariable Long Id, @PathVariable Long idArticulo) {
        return servicioCarrito.borrarLinea(Id, idArticulo);
    }
}
