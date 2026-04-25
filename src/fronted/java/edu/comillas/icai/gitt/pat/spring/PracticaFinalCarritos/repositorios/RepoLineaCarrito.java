package edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.repositorios;


import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity.LineaCarrito;
import org.springframework.data.repository.CrudRepository;

public interface RepoLineaCarrito extends CrudRepository<LineaCarrito, Long> {

}