Vue.createApp({
    data() {
        return {
            producto: null,
            carrito: [],
            lista: [],
            codigo: null,
            precio: null,
            nombre: null,
            stock: null,
            cant: null,
            total: 0,
            totalclp: 0,
            msg: "",
            buscarcodigo: "",
            tipopago: ""
        }
    },
    created() {

    },
    methods: {
        buscar() {
            axios.get(`/api/producto${this.buscarcodigo}`)
                .then(response => {
                    this.limpiar();
                    if (response.data === "") {
                        this.msg = "producto no encontrado";
                    } else {
                        console.log(response);
                        this.producto = response.data;
                        this.codigo = this.producto.barcode;
                        this.nombre = this.producto.nombre;
                        this.precio = this.producto.precio;
                        this.stock = this.producto.stock;
                        this.msg = `producto ${this.nombre} encontrado...`;
                    }
                })
                .catch(err => {
                    this.limpiar();
                    console.log(err);
                })
        },
        agregar() {
            let index = this.carrito.findIndex(producto => producto.codigo === this.codigo);
            console.log(index);
            if (this.codigo !== null && this.cant > 0 && this.cant <= this.stock && index == -1) {
                let aux = {
                    codigo: this.codigo,
                    nombre: this.nombre,
                    cantidad: this.cant,
                    precio: this.precio,
                }
                this.total = (aux.cantidad * aux.precio) + this.total;
                this.carrito.push(aux);
                this.formatomoneda();
                this.msg = `producto ${this.nombre} agregado al carrito`;
                this.limpiar();
            } else {
                this.msg = "Ocurrio un error...";
            }
        },
        formatomoneda() {
            this.totalclp = new Intl.NumberFormat('es-CL').format(this.total);
        },
        eliminar(codigo) {
            let index = this.carrito.findIndex(producto => producto.codigo === codigo);
            if (index !== -1) {
                this.total = this.total - (this.carrito[index].cantidad * this.carrito[index].precio);
                this.formatomoneda();
                this.limpiar();
                this.carrito.splice(index, 1);
                this.msg = "Producto eliminado...";
            } else {
                this.limpiar();
                this.msg = "producto no encontrado(?)...";
            }
        },
        completarVenta() {
            if (this.carrito.length > 0 && this.tipopago !== null && this.total > 0) {
                let compra = {
                    pago: "",
                    total: 0,
                    productos: []
                }
                let mapproductos = this.carrito.map(producto => {
                    let aux = {
                        barcode: producto.codigo,
                        cantidad: producto.cantidad
                    }
                    return aux;
                });
                compra.total = this.total;
                compra.pago = this.tipopago;
                compra.productos = mapproductos;
                axios.post('/api/venta/crear',compra)
                    .then(response => {
                        console.log(response)
                    })
                    .catch(err => {
                        console.log(err)
                    });
            } else {
                this.limpiar();
                this.msg = "la venta no se pudo completar...";
            }
        },
        limpiar() {
            this.producto = null;
            this.codigo = null;
            this.codigo = null;
            this.cant = null;
            this.nombre = null;
            this.precio = null;
            this.stock = null;
            this.buscarcodigo = null;
            this.msg = "";
        }

    }
}).mount('#app')