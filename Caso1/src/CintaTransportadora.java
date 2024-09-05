import java.util.ArrayList;
import java.util.List;

public class CintaTransportadora {
    List<Producto> productos = new ArrayList<>();
    private final int capacidad = 1;

    public CintaTransportadora() {
    }

    public void colocarEnCinta(Producto producto) {
        while (true) {
            synchronized (this) {
                if (productos.size() < capacidad) {
                    productos.add(producto);
                    break; 
                }
            }
            Thread.yield();
            try {
                Thread.sleep(10); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Producto retirarDeCinta() {
        Producto producto = null;
        while (true) {
            synchronized (this) {
                if (!productos.isEmpty()) {
                    producto = productos.remove(0);
                    break; 
                }
            }
            Thread.yield();
            try {
                Thread.sleep(10); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return producto;
    }
}
