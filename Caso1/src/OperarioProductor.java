public class OperarioProductor extends Thread{
    TipoProducto tipoOperario;
    int numProductos;
    Deposito depositoProduccion;

    public OperarioProductor(TipoProducto tipoOperario, int numProductos, Deposito depositoProduccion){
        this.tipoOperario = tipoOperario;
        this.numProductos = numProductos;
        this.depositoProduccion = depositoProduccion;
    }
    public void run() {
        while (numProductos >= 0) {
            synchronized (depositoProduccion) {
                // Esperar hasta que haya espacio en el depósito
                while (!depositoProduccion.hayEspacio()) {
                    try {
                        depositoProduccion.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Crear y almacenar el producto solo si hay espacio
                Producto producto = crearProducto();
                Producto espera = depositoProduccion.almacenarProducto(producto);
                while (espera == null) {
                    // No se pudo almacenar el producto, esperar y volver a intentar
                    try {
                        depositoProduccion.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    espera = depositoProduccion.almacenarProducto(producto);
                }
                numProductos--;
            }
        }
    }

    public synchronized Producto crearProducto(){
        Producto producto;
        if (numProductos==0 && tipoOperario==TipoProducto.A){
            producto = new Producto(TipoProducto.FIN_A);
            //System.out.println("Fin_A");
        }else if (numProductos==0 && tipoOperario==TipoProducto.B){
            producto = new Producto(TipoProducto.FIN_B);
            //System.out.println("Fin_B");
        }else{
            producto = new Producto(tipoOperario);
        }
        return producto;
    }
}
