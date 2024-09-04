public class OperarioProductor extends Thread{
    TipoProducto tipoOperario;
    int numProductos;
    Deposito depositoProduccion;

    public OperarioProductor(TipoProducto tipoOperario, int numProductos, Deposito depositoProduccion){
        this.tipoOperario = tipoOperario;
        this.numProductos = numProductos;
        this.depositoProduccion = depositoProduccion;
    }
    public void run(){
        while(numProductos>0){
            Producto producto;
            numProductos--;
            if (numProductos==numProductos+1 && tipoOperario==TipoProducto.FIN_A){
                producto = new Producto();
                producto.inicializar(TipoProducto.FIN_A);

                
            }else if (numProductos==numProductos+1 && tipoOperario==TipoProducto.FIN_B){
                producto = new Producto();
                producto.inicializar(TipoProducto.FIN_B);
            }else{
                producto = new Producto();
                producto.inicializar(tipoOperario);
            }
            depositoProduccion.almacenarProducto(producto);
        }
    }
}
