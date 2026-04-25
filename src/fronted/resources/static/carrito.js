function cargarCarrito() {
    const idCarrito = localStorage.getItem("idCarrito");

    if (idCarrito === null) {
        alert("No hay ningún carrito creado");
        return;
    }

    fetch("/api/carrito/" + idCarrito)
        .then(respuesta => {
            if (!respuesta.ok) {
                throw respuesta.status;
            }
            return respuesta.json();
        })
        .then(carrito => {
            const tabla = document.getElementById("tabla-carrito");
            tabla.innerHTML = "";

            carrito.lineas.forEach(linea => {
                tabla.innerHTML += `
                <tr>
                    <td>${linea.idArticulo}</td>
                    <td>${linea.numeroUnidades}</td>
                    <td>${linea.precioUnitario} €</td>
                    <td>${linea.coste} €</td>
                    <td>
                        <button onclick="borrarLinea(${linea.idArticulo})">
                            Borrar producto
                        </button>
                    </td>
                </tr>
            `;
            });

            document.getElementById("total-carrito").textContent =
                "Total: " + carrito.precioTotal + " €";
        })
        .catch(error => {
            alert("Error al cargar carrito: " + error);
            console.log(error);
        });
}

function borrarLinea(idArticulo) {
    const idCarrito = localStorage.getItem("idCarrito");

    fetch("/api/carrito/" + idCarrito + "/linea/" + idArticulo, {
        method: "DELETE"
    })
        .then(respuesta => {
            if (!respuesta.ok) {
                throw respuesta.status;
            }
            return respuesta.json();
        })
        .then(carrito => {
            cargarCarrito();
        })
        .catch(error => {
            alert("Error al borrar producto: " + error);
            console.log(error);
        });
}

function borrarCarrito() {
    const idCarrito = localStorage.getItem("idCarrito");

    if (idCarrito === null) {
        alert("No hay carrito para borrar");
        return;
    }

    fetch("/api/carrito/" + idCarrito, {
        method: "DELETE"
    })
        .then(respuesta => {
            if (!respuesta.ok) {
                throw respuesta.status;
            }

            localStorage.removeItem("idCarrito");

            document.getElementById("tabla-carrito").innerHTML = "";
            document.getElementById("total-carrito").textContent = "Total: 0 €";

            alert("Carrito borrado");
        })
        .catch(error => {
            alert("Error al borrar carrito: " + error);
            console.log(error);
        });
}

window.addEventListener("load", function() {
    cargarCarrito();
});