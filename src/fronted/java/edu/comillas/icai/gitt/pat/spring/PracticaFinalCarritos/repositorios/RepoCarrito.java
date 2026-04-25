package edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.repositorios;
import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity.Carrito;
import org.springframework.data.repository.CrudRepository;


import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity.LineaCarrito;
import org.springframework.data.repository.CrudRepository;


public interface RepoCarrito extends CrudRepository<Carrito, Long> {
    Carrito findByIdCarrito(Long idCarrito);
    Carrito findByCorreo(String correo);
    Carrito findByIdUsuario(String idUsuario);

}


