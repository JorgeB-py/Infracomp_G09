public class OperarioInterno extends Thread{

    int numProductosFinA=0;
    int numProductosFinB=0;
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
        while (numProductosFinA<2 || numProductosFinB<2){
        if (tipoOperario==TipoOperario.A){
                Producto producto=depositoProduccion.retirarProducto();
                while (producto==null){
                    Thread.yield();
                    producto=depositoProduccion.retirarProducto();
                }
                if ((producto.tipoProducto==TipoProducto.FIN_A)){
                    numProductosFinA++;
                }else if (producto.tipoProducto==TipoProducto.FIN_B){
                    numProductosFinB++;
                }
                moverACinta(producto);
        }else{
                Producto producto=cintaTransportadora.retirarDeCinta();
                while (producto==null){
                    Thread.yield();
                    producto=cintaTransportadora.retirarDeCinta();
                }
                if (producto.tipoProducto==TipoProducto.FIN_A){
                    numProductosFinA++;
                }else if (producto.tipoProducto==TipoProducto.FIN_B){
                    numProductosFinB++;
                }
                moverADeposito(producto);
            }
        }
    }

    public Producto moverACinta(Producto producto){
        boolean espera = cintaTransportadora.colocarEnCinta(producto);
        while(!espera){
            Thread.yield();
            espera = cintaTransportadora.colocarEnCinta(producto);
        }
        return producto;
    }

    public Producto moverADeposito(Producto producto){
        Producto esperar=depositoDistribucion.almacenarProducto(producto);
        while(esperar==null){
            Thread.yield();
            esperar=depositoDistribucion.almacenarProducto(producto);
        }
        return producto;
    }
    
}
