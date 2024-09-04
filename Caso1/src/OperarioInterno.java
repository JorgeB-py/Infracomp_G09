public class OperarioInterno extends Thread{

    int numProductosFin;

    Deposito depositoProduccion;
    Deposito depositoDistribucion;
    public static CintaTransportadora cintaTransportadora = new CintaTransportadora();

    public OperarioInterno(Deposito depositoProduccion, Deposito depositoDistribucion){
        this.numProductosFin = 0;
        this.depositoDistribucion = depositoDistribucion;
        this.depositoProduccion = depositoProduccion;
    }

    public void run(){
    }

    public Producto moverACinta(Producto producto){
        producto=depositoProduccion.retirarProducto(producto.tipoProducto);
        cintaTransportadora.colocarEnCinta(producto);
        if (producto.tipoProducto==TipoProducto.FIN_A || producto.tipoProducto==TipoProducto.FIN_B){
            numProductosFin++;
        }
        return producto;
    }

    public Producto moverADeposito(Producto producto){
        producto=cintaTransportadora.retirarDeCinta();
        depositoDistribucion.almacenarProducto(producto);
        return producto;
    }
    
}
