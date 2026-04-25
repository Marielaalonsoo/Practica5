package edu.comillas.icai.gitt.pat.spring.PracticaFinalCarritos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LineaCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idLinea;

    @Column(nullable = false)
    public Long idArticulo;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "idCarrito", nullable = false)
    public Carrito carrito;

    @Column(nullable = false)
    public Long precioUnitario;

    @Column(nullable = false)
    public Long numeroUnidades;

    @Column(nullable = false) //puedes no moterle nda porque asi lo inicializa calculandoselo a partir de los otros?????
    public Long coste; //comprobar que nollable

    // metodo para calcular el precio total  de la linea


    public long costelinea(){
        return (this.coste= this.precioUnitario * this.numeroUnidades) ;
    }

}