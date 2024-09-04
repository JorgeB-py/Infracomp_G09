import java.util.List;

public class Deposito {
    int capacidad;
    List<Producto> productos;

    public Deposito(int capacidad){
        this.capacidad=capacidad;
    }

    public synchronized void almacenarProducto(Producto producto){
        productos.addLast(producto);
    }

    public synchronized Producto retirarProducto(TipoProducto tipoProducto){
        for(Producto producto: productos){
            if(producto.tipoProducto == tipoProducto){
                productos.remove(producto);
                return producto;
            }
        }
        return null;
    }
}
