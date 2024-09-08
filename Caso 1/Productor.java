public class Productor extends Thread {
    private TipoProdDis tipo;
    private Deposito deposito;
    private int cantidad;
    private int id;

    public Productor(TipoProdDis tipo, Deposito deposito, int cantidad, int id) {
        this.tipo = tipo;
        this.deposito = deposito;
        this.cantidad = cantidad;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < cantidad; i++) {
            Producto producto = new Producto(TipoProducto.valueOf(tipo.toString()));
            deposito.almacenar(producto);
        }
        TipoProducto tProducto = tipo == TipoProdDis.A ? TipoProducto.FIN_A : TipoProducto.FIN_B;
        Producto producto = new Producto(tProducto);
        deposito.almacenar(producto);
        System.out.println("### Productor " + tipo + id + " ha terminado la producción con " + tProducto);
    }

}
