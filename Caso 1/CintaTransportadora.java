public class CintaTransportadora {
    private Producto producto;

    public CintaTransportadora() {
        this.producto = null;
    }

    public synchronized void almacenar(Producto producto) {
        this.producto = producto;
    }

    public synchronized Producto extraer() {
        Producto producto = this.producto;
        this.producto = null;
        return producto;
    }

    public synchronized boolean estaVacio() {
        return producto == null;
    }

}
