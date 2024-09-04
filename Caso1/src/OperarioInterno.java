public class OperarioInterno extends Thread{

    int numProductosFin;
    TipoOperario tipoOperario;

    Deposito depositoProduccion;
    Deposito depositoDistribucion;
    public static CintaTransportadora cintaTransportadora = new CintaTransportadora();

    public OperarioInterno(Deposito depositoProduccion, Deposito depositoDistribucion, TipoOperario tipoOperario){
        this.numProductosFin = 0;
        this.depositoDistribucion = depositoDistribucion;
        this.depositoProduccion = depositoProduccion;
        this.tipoOperario = tipoOperario;
    }

    public void run(){
        while(numProductosFin<4){
            if(tipoOperario==TipoOperario.A){
                moverACinta();
            }else{
                cintaTransportadora.retirarDeCinta();
                moverADeposito();
            }
        }
    }

    public Producto moverACinta(){
        Producto producto=depositoProduccion.retirarProducto();
        cintaTransportadora.colocarEnCinta(producto);
        if (producto.tipoProducto==TipoProducto.FIN_A || producto.tipoProducto==TipoProducto.FIN_B){
            numProductosFin++;
        }
        return producto;
    }

    public Producto moverADeposito(){
        Producto producto=cintaTransportadora.retirarDeCinta();
        depositoDistribucion.almacenarProducto(producto);
        return producto;
    }
    
}
