import java.util.ArrayList;
import java.util.List;

public class Deposito {
    int capacidad;
    List<Producto> productos = new ArrayList<Producto>();

    public Deposito(int capacidad){
        this.capacidad=capacidad;
    }

    public synchronized void almacenarProducto(Producto producto){
        while(!hayEspacio()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productos.addLast(producto);
        capacidad--;
        notify();
    }

    public synchronized Producto retirarProducto(TipoProducto tipoProducto){
        while(!hayProducto(tipoProducto)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public synchronized Producto retirarProducto(){
        while (productos.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto producto = productos.remove(0);
        capacidad++;
        notifyAll(); 
        return producto;
    }

    public synchronized boolean hayEspacio(){
        return capacidad>0;
    }

    public synchronized boolean hayProducto(TipoProducto tipoProducto){
        for(Producto producto: productos){
            if(producto.tipoProducto == tipoProducto){
                return true;
            }
        }
        return false;
    }
}
