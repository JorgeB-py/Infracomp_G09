import java.util.ArrayList;
import java.util.List;

public class CintaTransportadora {
    List<Producto> productos = new ArrayList<>();

    public CintaTransportadora(){
    }

    public void colocarEnCinta(Producto producto){
        while (productos.size()==1){
            Thread.yield();
        }
        synchronized(productos){
            productos.add(producto);
        }
    }

    public Producto retirarDeCinta(){
        while(productos.isEmpty()){
            Thread.yield();
        }
        synchronized(productos){
            return productos.removeFirst();
        }
    }
}
