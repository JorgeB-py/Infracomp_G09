public class OperarioProductor extends Thread{
    TipoOperario tipoOperario;
    int numProductos;

    public OperarioProductor(TipoOperario tipoOperario, int numProductos){
        this.tipoOperario = tipoOperario;
        this.numProductos = numProductos;
    }
    public void run(){

    }
}
