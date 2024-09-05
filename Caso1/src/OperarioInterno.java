public class OperarioInterno extends Thread{

    public static int numProductosFin=0;
    TipoOperario tipoOperario;

    Deposito depositoProduccion;
    Deposito depositoDistribucion;
    CintaTransportadora cintaTransportadora;

    public OperarioInterno(Deposito depositoProduccion, Deposito depositoDistribucion, TipoOperario tipoOperario, CintaTransportadora cintaTransportadora){
        this.depositoDistribucion = depositoDistribucion;
        this.depositoProduccion = depositoProduccion;
        this.tipoOperario = tipoOperario;
        this.cintaTransportadora = cintaTransportadora;
    }

    public void run(){
        while(numProductosFin<4){
            if(tipoOperario==TipoOperario.A){
                moverACinta();
            }else{
                moverADeposito();
            }
        }
    }

    public Producto moverACinta(){
        Producto producto=depositoProduccion.retirarProducto();
        if (producto.tipoProducto == TipoProducto.FIN_A || producto.tipoProducto == TipoProducto.FIN_B) {
            return producto;
        }
        cintaTransportadora.colocarEnCinta(producto);
        return producto;
    }

    public Producto moverADeposito(){
        Producto producto=cintaTransportadora.retirarDeCinta();
        if (producto.tipoProducto==TipoProducto.FIN_A || producto.tipoProducto==TipoProducto.FIN_B){
            numProductosFin++;
        }
        depositoDistribucion.almacenarProducto(producto);
        return producto;
    }
    
}
