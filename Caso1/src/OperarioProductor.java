public class OperarioProductor extends Thread{
    TipoProducto tipoOperario;
    int numProductos;
    public Deposito depositoProduccion;

    public OperarioProductor(TipoProducto tipoOperario, int numProductos, Deposito depositoProduccion){
        this.tipoOperario = tipoOperario;
        this.numProductos = numProductos;
        this.depositoProduccion = depositoProduccion;
    }
    public void run(){
        Producto producto;
        for(int i = 0; i < numProductos; i++){
            if (i==numProductos-1 && tipoOperario==TipoProducto.FIN_A){
                producto = new Producto(TipoProducto.FIN_A);
                
            }else if (i==numProductos-1 && tipoOperario==TipoProducto.FIN_B){
                producto = new Producto(TipoProducto.FIN_B);
            }else{
                producto = new Producto(tipoOperario);
            }
            while(!depositoProduccion.hayEspacio()){
                try {
                    depositoProduccion.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            depositoProduccion.almacenarProducto(producto);
            }
        }
    }
}
