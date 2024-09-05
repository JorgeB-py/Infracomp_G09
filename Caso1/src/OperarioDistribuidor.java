public class OperarioDistribuidor extends Thread{
    TipoProducto tipoOperario;
    Deposito depositoDistribucion;

    public OperarioDistribuidor(TipoProducto tipoOperario, Deposito depositoDistribucion){
        this.tipoOperario = tipoOperario;
        this.depositoDistribucion = depositoDistribucion;
    }

    public void run(){
        if(tipoOperario==TipoProducto.A){
            int numA=1;
            while(numA==1){
                Producto producto=depositoDistribucion.retirarProducto(TipoProducto.A);
                if (producto.tipoProducto==TipoProducto.FIN_A){
                    numA++;
                }else{
                    System.out.println("Producto A retirado");
                }
            }
        }else{
            int numB=1;
            while(numB==1){
                Producto producto=depositoDistribucion.retirarProducto(TipoProducto.B);
                if(producto.tipoProducto==TipoProducto.FIN_B){
                    numB++;
                }else{
                    System.out.println("Producto B retirado");
                }
            }
        }
        
    }
}
