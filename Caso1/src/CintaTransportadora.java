import java.util.ArrayList;
import java.util.List;

public class CintaTransportadora {
    List<Producto> productos = new ArrayList<>();

    public CintaTransportadora(){
    }

    public synchronized void colocarEnCinta(Producto producto){
        productos.addLast(producto);
    }

    public synchronized Producto retirarDeCinta(){
        return productos.removeFirst();
    }
}
