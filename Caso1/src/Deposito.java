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
    }

    public synchronized Producto retirarProducto(TipoProducto tipoProducto){
        for(Producto producto: productos){
            if(producto.tipoProducto == tipoProducto){
                productos.remove(producto);
                capacidad++;
                notify();
                return producto;
            }else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public synchronized Producto retirarProducto(){
        if (!productos.isEmpty()){
            Producto producto = productos.removeFirst();
            capacidad++;
            notifyAll();
            return producto;
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
        return productos.removeFirst();
    }
        
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
