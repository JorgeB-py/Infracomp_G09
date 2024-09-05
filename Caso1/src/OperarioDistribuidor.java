public class OperarioDistribuidor extends Thread{
    TipoProducto tipoOperario;
    Deposito depositoDistribucion;

    public OperarioDistribuidor(TipoProducto tipoOperario, Deposito depositoDistribucion){
        this.tipoOperario = tipoOperario;
        this.depositoDistribucion = depositoDistribucion;
    }

    public void run(){
        while(OperarioInterno.numProductosFin<4){
            Producto producto = depositoDistribucion.retirarProducto(tipoOperario);
            System.out.println("Producto retirado: "+producto.tipoProducto);
        }
        
    }
}
