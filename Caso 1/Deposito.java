import java.util.ArrayList;
import java.util.List;

public class Deposito {
    private int capacidad;
    private List<Producto> productos;

    public Deposito(int capacidad) {
        this.capacidad = capacidad;
        productos = new ArrayList<Producto>();
    }

    public synchronized void almacenar(Producto producto) {
        while (productos.size() == capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productos.add(producto);
        System.out.println("-> Producto " + producto.getTipo() + " almacenado en el depósito de producción");
        notifyAll();
    }

    public synchronized void almacenar(Producto producto, boolean interno) {
        productos.add(producto);
        notifyAll();
    }

    public synchronized Producto extraer(TipoProducto tipo) {
        while (productos.isEmpty() || !contieneProducto(tipo)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int index = indexOfTipo(tipo);
        Producto producto = productos.remove(index);
        System.out.println("-----> Producto " + producto.getTipo() + " extraído del depósito de distribución");
        notifyAll();
        return producto;
    }

    public synchronized Producto extraer() {
        Producto producto = productos.remove(0);
        notifyAll();
        return producto;
    }

    public synchronized boolean estaVacio() {
        return productos.isEmpty();
    }

    public synchronized boolean estaLleno() {
        return productos.size() == capacidad;
    }

    private boolean contieneProducto(TipoProducto tipo) {
        for (Producto producto : productos) {
            if (producto.getTipo() == tipo) {
                return true;
            } else if (producto.getTipo() == TipoProducto.FIN_A && tipo == TipoProducto.A) {
                return true;
            } else if (producto.getTipo() == TipoProducto.FIN_B && tipo == TipoProducto.B) {
                return true;
            }
        }
        return false;
    }

    private int indexOfTipo(TipoProducto tipo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getTipo() == tipo) {
                return i;
            } else if (productos.get(i).getTipo() == TipoProducto.FIN_A && tipo == TipoProducto.A) {
                return i;
            } else if (productos.get(i).getTipo() == TipoProducto.FIN_B && tipo == TipoProducto.B) {
                return i;
            }
        }
        return -1;
    }

}
