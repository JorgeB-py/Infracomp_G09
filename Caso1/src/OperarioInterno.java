public class OperarioInterno extends Thread{

    int numProductosFin;

    public static Deposito depositoProduccion;
    public static Deposito depositoDistribucion;
    public static CintaTransportadora cintaTransportadora;

    public OperarioInterno(){
        this.numProductosFin = 0;
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
