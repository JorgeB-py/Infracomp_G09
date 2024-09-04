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
            if (numProductos==1 && tipoOperario==TipoProducto.A){
                producto = new Producto(TipoProducto.FIN_A);
            }else if (numProductos==1 && tipoOperario==TipoProducto.B){
                producto = new Producto(TipoProducto.FIN_B);
            }else{
                producto = new Producto(tipoOperario);
            }
            depositoProduccion.almacenarProducto(producto);
            numProductos--;
        }
    }
}
