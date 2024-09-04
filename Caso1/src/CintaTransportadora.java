import java.util.ArrayList;
import java.util.List;

public class CintaTransportadora {
    List<Producto> productos = new ArrayList<>();

    public CintaTransportadora(){
    }

    public synchronized void colocarEnCinta(Producto producto){
        productos.addLast(producto);
        Thread.yield();
    }

    public synchronized Producto retirarDeCinta(){
        if (!productos.isEmpty()){
            return productos.removeFirst();
        }else{
            Thread.yield();
            return productos.removeFirst();
        }
    }
}
