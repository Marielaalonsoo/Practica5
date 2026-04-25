function anadirProducto(idArticulo, precioUnitario, idCantidad) {
    const idCarrito = localStorage.getItem("idCarrito");

    if (idCarrito === null) {
        alert("Primero tienes que crear un carrito en Inicio");
        return;
    }

    const cantidad = Number(document.getElementById(idCantidad).value);

    if (cantidad < 1) {
        alert("La cantidad debe ser al menos 1");
        return;
    }

    const linea = {
        idArticulo: idArticulo,
        precioUnitario: precioUnitario,
        numeroUnidades: cantidad,
        coste: precioUnitario * cantidad
    };

    fetch("/api/carrito/" + idCarrito + "/linea", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(linea)
    })
        .then(respuesta => {
            if (!respuesta.ok) {
                throw respuesta.status;
            }
            return respuesta.json();
        })
        .then(carrito => {
            alert("Producto añadido al carrito");
            console.log(carrito);
        })
        .catch(error => {
            alert("Error al añadir producto: " + error);
            console.log(error);
        });
}