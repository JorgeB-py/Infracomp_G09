public class OperarioInterno extends Thread{

    int numProductosFin;
    TipoOperario tipoOperario;

    Deposito depositoProduccion;
    Deposito depositoDistribucion;
    CintaTransportadora cintaTransportadora;

    public OperarioInterno(Deposito depositoProduccion, Deposito depositoDistribucion, TipoOperario tipoOperario, CintaTransportadora cintaTransportadora){
        this.numProductosFin = 0;
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
