import java.util.List;

public class Deposito {
    int capacidad;
    List<Producto> productos;

    public Deposito(int capacidad){
        this.capacidad=capacidad;
    }

    public synchronized void almacenarProducto(Producto producto){
        productos.addLast(producto);
        capacidad--;
    }

    public synchronized Producto retirarProducto(TipoProducto tipoProducto){
        for(Producto producto: productos){
            if(producto.tipoProducto == tipoProducto){
                productos.remove(producto);
                capacidad++;
                notify();
                return producto;
            }
        }
        return null;
    }
    public synchronized boolean hayEspacio(){
        return capacidad>0;
    }
}
