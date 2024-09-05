import java.util.ArrayList;
import java.util.List;

public class Deposito {
    int capacidad;
    List<Producto> productosA = new ArrayList<Producto>();
    List<Producto> productosB = new ArrayList<Producto>();
    List<Producto> productos = new ArrayList<Producto>();

    public Deposito(int capacidad){
        this.capacidad=capacidad;
    }

    public synchronized Producto almacenarProducto(Producto producto){
        while(!hayEspacio()){
            return null;
        }
        if (producto.tipoProducto==TipoProducto.A || producto.tipoProducto==TipoProducto.FIN_A){
            productosA.add(producto);
        }else{
            productosB.add(producto);
        }
        productos.add(producto);
        capacidad--;
        notifyAll();
        return producto;
    }

    public synchronized Producto retirarProducto(TipoProducto tipoProducto){
        if (tipoProducto==TipoProducto.A){
            while(productosA.size()==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Producto producto = productosA.removeFirst();
            capacidad++;
            notifyAll();
            return producto;
        }else{
            while(productosB.size()==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Producto producto = productosB.removeFirst();
            capacidad++;
            notifyAll();
            return producto;
        }
    }

    public synchronized Producto retirarProducto(){
        if (productos.size()==0){
            return null;
        }
        Producto producto = productos.remove(0);
        capacidad++;
        notifyAll();
        return producto;
    }

    public synchronized boolean hayEspacio(){
        return capacidad>0;
    }

    public synchronized boolean hayA(){
        return productosA.size()>0;
    }
    public synchronized boolean hayB(){
        return productosB.size()>0;
    }
}
