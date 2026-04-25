window.addEventListener("load", function() {

    const form = document.getElementById("form-carrito");
    const mensaje = document.getElementById("mensaje-carrito");

    console.log("FORM:", form);
    console.log("MENSAJE:", mensaje);

    form.addEventListener("submit", function(event) {
        event.preventDefault();

        const carrito = {
            idUsuario: document.getElementById("idUsuario").value,
            correo: document.getElementById("correo").value
        };

        fetch("/api/carrito", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(carrito)
        })
            .then(respuesta => {
                if (!respuesta.ok) {
                    throw respuesta.status;
                }
                return respuesta.json();
            })
            .then(carritoCreado => {
                localStorage.setItem("idCarrito", carritoCreado.idCarrito);
                mensaje.textContent = "Carrito creado con id: " + carritoCreado.idCarrito;
            })
            .catch(error => {
                mensaje.textContent = "Error al crear el carrito";
                console.log(error);
            });
    });

});