public class Producto{
    TipoProducto tipoProducto;

    public Producto(){
    }

    public synchronized void inicializar(TipoProducto tipoProducto){
        this.tipoProducto=tipoProducto;
    }
}