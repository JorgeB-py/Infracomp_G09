import java.util.ArrayList;
import java.util.List;

public class CintaTransportadora {
    List<Producto> productos = new ArrayList<>();

    public CintaTransportadora() {
    }

    public boolean colocarEnCinta(Producto producto){
        if (productos.size()>0){
            return false;
        }
        productos.add(producto);
        return true;
    }

    public synchronized Producto retirarDeCinta(){
        Producto producto = productos.remove(0);
        if(productos.isEmpty()){
            return null;
        }
        return producto;
    }
}
