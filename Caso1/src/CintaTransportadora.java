import java.util.ArrayList;
import java.util.List;

public class CintaTransportadora {
    List<Producto> productos = new ArrayList<>();

    public CintaTransportadora(){
    }

    public boolean colocarEnCinta(Producto producto){
        if (productos.size()>0){
            return false;
        }
        productos.add(producto);
        return true;
    }

    public synchronized Producto retirarDeCinta(){
        if(productos.isEmpty()){
            return null;
        }
        return productos.remove(0);
    }
}
